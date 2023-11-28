package com.xsz.opennlp.demo1;

import opennlp.tools.langdetect.*;
import opennlp.tools.ml.perceptron.PerceptronTrainer;
import opennlp.tools.util.*;
import opennlp.tools.util.model.ModelUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Training {

    public static void main(String[] args) throws IOException {
        train();
    }

    public static void train() throws IOException {
        InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File("C:\\Users\\Brad\\IdeaProjects\\opennlpdemo\\src\\main\\java\\com\\xsz\\opennlp\\demo1\\corpus.txt"));

        ObjectStream<String> lineStream =  new PlainTextByLineStream(inputStreamFactory, StandardCharsets.UTF_8);
        ObjectStream<LanguageSample> sampleStream = new LanguageDetectorSampleStream(lineStream);

        TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
        params.put(TrainingParameters.ALGORITHM_PARAM,  PerceptronTrainer.PERCEPTRON_VALUE);
        params.put(TrainingParameters.CUTOFF_PARAM, 0);

        LanguageDetectorFactory factory = new LanguageDetectorFactory();

        LanguageDetectorModel model = LanguageDetectorME.train(sampleStream, params, factory);
        model.serialize(new File("langdetect.bin"));
    }
    }

