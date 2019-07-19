package cn.abr.common;

import com.squareup.kotlinpoet.ClassName;
import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.FileSpec;
import com.squareup.kotlinpoet.FunSpec;
import com.squareup.kotlinpoet.KModifier;
import com.squareup.kotlinpoet.ParameterSpec;
import com.squareup.kotlinpoet.ParameterizedTypeName;
import com.squareup.kotlinpoet.PropertySpec;
import com.squareup.kotlinpoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cn.abr.common.entity.Article;
import cn.abr.common.net.api.Repository;
import cn.abr.inabr.base.BaseModel;
import cn.abr.inabr.base.BaseView;
import io.reactivex.Flowable;

public class Test {
    private static String appPackage = "cn.abr.qichang_news.mvp";

    private static String packageName = appPackage;

    private static List<FunSpec> modelFun = new ArrayList<>();
    private static List<FunSpec> modelOverrideFun = new ArrayList<>();
    private static List<FunSpec> presenterFun = new ArrayList<>();
    private static List<FunSpec> viewFun = new ArrayList<>();
    private static List<FunSpec> viewOverrideFun = new ArrayList<>();

    public static void main(String[] args) {
        addModelFun("getArticle", Article.class, new ParameterEntity("id", String.class));
        addViewFun("getArticleId", String.class);
        addViewFun("getArticleId", String.class, new ParameterEntity("article", Article.class));
        addPresenterFun("getArticle");
        createMVP("Login");
    }

    private static void addPresenterFun(String name) {
        FunSpec funSpec = createPresenterFun(name);
        presenterFun.add(funSpec);
        addOverridePresenterFun(name);
    }

    private static void addOverridePresenterFun(String name) {
        FunSpec build = FunSpec.builder(name).addModifiers(KModifier.OVERRIDE).build();
        viewOverrideFun.add(build);
    }

    private static FunSpec createPresenterFun(String name) {
        FunSpec build = FunSpec.builder(name).addModifiers(KModifier.ABSTRACT).build();
        return build;
    }

    private static void addViewFun(String name, Class returnClass, ParameterEntity... parameterEntities) {
        FunSpec funSpec = createViewFun(name, returnClass, parameterEntities);
        viewFun.add(funSpec);
    }


    private static FunSpec createViewFun(String name, Class returnClass, ParameterEntity[] parameterEntities) {
        List<ParameterSpec> parameters = createParameters(parameterEntities);
        FunSpec funSpec = FunSpec.builder(name).returns(returnClass).addModifiers(KModifier.ABSTRACT).addParameters(parameters).build();
        return funSpec;
    }

    private static void createMVP(String name) {
        packageName += "." + name.toLowerCase();
        createContract(name);
        createModel(name);
        createPresenter(name);
    }

    private static void addModelFun(String funName, Class returnClass, ParameterEntity... parameterEntities) {
        FunSpec modelFun = createModelFun(funName, returnClass, parameterEntities);
        Test.modelFun.add(modelFun);


        addOverrideModelFun(funName, returnClass, parameterEntities);
    }

    private static void addOverrideModelFun(String funName, Class<Article> returnClass, ParameterEntity[] parameterEntities) {
        List<ParameterSpec> parameters = createParameters(parameterEntities);

        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName
                .get(Flowable.class, returnClass);


        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("return %T.%N(");
        for (int i = 0; i < parameterEntities.length; i++) {
            stringBuffer.append(parameterEntities[i].name);
            if (i != parameterEntities.length - 1) {
                stringBuffer.append(",");
            } else {
                stringBuffer.append(",false)");
            }
        }

        FunSpec build = FunSpec.builder(funName).addModifiers(KModifier.OVERRIDE).returns(parameterizedTypeName).addParameters(parameters)
                .addStatement(stringBuffer.toString(), Repository.class,funName).build();
        modelOverrideFun.add(build);
    }

    private static FunSpec createModelFun(String funName, Class returnClass, ParameterEntity... parameterEntities) {
        List<ParameterSpec> parameters = createParameters(parameterEntities);

        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName
                .get(Flowable.class, returnClass);

        FunSpec build = FunSpec.builder(funName).addModifiers(KModifier.ABSTRACT).returns(parameterizedTypeName).addParameters(parameters).build();
        return build;
    }

    private static List<ParameterSpec> createParameters(ParameterEntity... parameterEntities) {
        List<ParameterSpec> list = new ArrayList<>();
        for (ParameterEntity parameterEntity : parameterEntities) {
            ParameterSpec parameter = createParameter(parameterEntity.name, parameterEntity.classType);
            list.add(parameter);
        }
        return list;
    }

    private static ParameterSpec createParameter(String name, Class classType) {
        ParameterSpec build = ParameterSpec.builder(name,  ClassName.bestGuess(classType.getSimpleName())).build();
        return build;
    }

    static class ParameterEntity {
        private String name;
        private Class classType;

        public ParameterEntity(String name, Class classType) {
            this.name = name;
            this.classType = classType;
        }
    }

    private static void createContract(String name) {
        String fileName = name + "Contract";
        try {
            TypeSpec presenter = TypeSpec.interfaceBuilder("Presenter")
                    .addSuperinterface(ParameterizedTypeName
                            .get(ClassName.bestGuess("cn.abr.inabr.base." + "BasePresenter"),
                                    ClassName.bestGuess("View"), ClassName.bestGuess("Model")), CodeBlock.builder().build())
                    .addFunctions(presenterFun)
                    .build();
            TypeSpec model = TypeSpec.classBuilder("Model")
                    .addModifiers(KModifier.ABSTRACT)
                    .addFunctions(modelFun)
                    .superclass(BaseModel.class).build();
            TypeSpec view = TypeSpec.interfaceBuilder("View")
                    .addSuperinterface(BaseView.class, CodeBlock.builder().build())
                    .addFunctions(viewFun)
                    .build();
            TypeSpec typeSpec = TypeSpec.interfaceBuilder(fileName).addType(model).addType(view).addType(presenter).build();
            FileSpec fileSpec = FileSpec.builder(packageName, fileName).addType(typeSpec).build();
            fileSpec.writeTo(new File("app/src/main/java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createModel(String name) {
        String contractName = name + "Contract";
        String modelName = name + "Model";
        try {
            TypeSpec typeSpec = TypeSpec.classBuilder(modelName)
                    .primaryConstructor(FunSpec.constructorBuilder().addAnnotation(Inject.class).build())
                    .superclass(ClassName.bestGuess(contractName + ".Model"))
                    .addFunctions(modelOverrideFun)
                    .build();
            FileSpec fileSpec = FileSpec.builder(packageName, modelName).addType(typeSpec).build();
            fileSpec.writeTo(new File("app/src/main/java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createPresenter(String name) {
        String contractName = name + "Contract";
        String modelName = name + "Presenter";
        try {
            PropertySpec mView = PropertySpec.varBuilder("mView", ClassName.bestGuess(contractName + ".View"), KModifier.LATEINIT).build();
            PropertySpec mModel = PropertySpec.varBuilder("mModel", ClassName.bestGuess(contractName + ".Model"), KModifier.LATEINIT).build();
            FunSpec loadRepositories = FunSpec.builder("loadRepositories").addModifiers(KModifier.OVERRIDE).build();
            TypeSpec typeSpec = TypeSpec.classBuilder(modelName)
                    .primaryConstructor(FunSpec.constructorBuilder().addAnnotation(Inject.class).build())
                    .addSuperinterface(ClassName.bestGuess(contractName + ".Presenter"), CodeBlock.builder().build())
                    .addProperty(mView)
                    .addProperty(mModel)
                    .addFunction(loadRepositories)
                    .addFunctions(viewOverrideFun)
                    .build();
            FileSpec fileSpec = FileSpec.builder(packageName, modelName).addType(typeSpec).build();
            fileSpec.writeTo(new File("app/src/main/java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
