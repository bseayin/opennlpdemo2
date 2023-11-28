package com.xsz.opennlp.demo1;

import opennlp.tools.namefind.*;
import opennlp.tools.util.*;
import opennlp.tools.util.eval.FMeasure;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MenuFinderTrain {

    public static void main(String[] args) throws IOException {

        // TODO Auto-generated method stub

        String rootDir = System.getProperty("user.dir") + File.separator;

        String fileResourcesDir = rootDir + "resources" + File.separator;

        String modelResourcesDir = rootDir + "opennlpmodel" + File.separator;

        //训练数据的路径

        String filePath = fileResourcesDir + "menuFinder.txt";

        //训练后模型的保存路径

        String modelPath = modelResourcesDir + "en-ner-menu-my.bin";


        //按行读取数据

        InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File(filePath));

        ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);

        //按行读取数据

        ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);

        TokenNameFinderFactory factory =new TokenNameFinderFactory();

        //训练模型

        TokenNameFinderModel model =NameFinderME.train("en","menu", sampleStream,  TrainingParameters.defaultParams(),  factory);

        //保存模型

        FileOutputStream fos=new FileOutputStream(new File(modelPath));

        OutputStream modelOut = new BufferedOutputStream(fos);

        model.serialize(modelOut);


//评估模型

        TokenNameFinderEvaluator evaluator = new TokenNameFinderEvaluator(new NameFinderME(model));

        evaluator.evaluate(sampleStream);



        FMeasure result = evaluator.getFMeasure();

        System.out.println(result.toString());

    }

}
