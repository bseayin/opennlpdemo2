package com.xsz.opennlp.demo1;

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;

import opennlp.tools.namefind.TokenNameFinderModel;

import opennlp.tools.util.Span;



public class NameFinderPredit {



    public static void main(String[] args) throws IOException {

        // TODO Auto-generated method stub

        String rootDir = System.getProperty("user.dir") + File.separator;



        String fileResourcesDir = rootDir + "resources" + File.separator;

        String modelResourcesDir = rootDir + "opennlpmodel" + File.separator;



        //String filePath = fileResourcesDir + "sentenceDetector.txt";

//        String modelPath = modelResourcesDir + "en-ner-person.bin";
//        String modelPath = modelResourcesDir + "en-ner-person-my.bin";
        String modelPath = modelResourcesDir + "en-ner-menu-my.bin";



        InputStream modelIn = new FileInputStream(modelPath) ;

        //加载模型

        TokenNameFinderModel model = new TokenNameFinderModel(modelIn);

        //实例化模型

        NameFinderME nameFinder  = new NameFinderME(model);



        String tokens[] = new String[]{

                "Vinken",

                "is",

                "61",

                "years",

                "old",

                "Pierre",

                ".",

                "Pierre",

        };

        //命名检测

//Span 保存表示命名实体在tokens中的位置

        Span[] nameFinds= nameFinder.find(tokens);



        for(Span str:nameFinds){

            System.out.println("type:"+str.getType()+";Tostring:"+str.toString()+";length:"+nameFinds.length+"start:"+str.getStart()+";end:"+str.getEnd()+";name:"+tokens[str.getStart()]);

        }

    }

}
