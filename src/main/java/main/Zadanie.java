package main;


import main.config.Config;
import main.service.Service;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Zadanie {

    public static void main(String[] args) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        Config config = new Config();
        String path = config.uploadPath();

        JSONArray wellsParamListJson = (JSONArray) parser.parse(new FileReader(path + "wellParameters.json"));
        JSONArray wellsListJson = (JSONArray) parser.parse(new FileReader(path + "wells.json"));
        JSONArray departListJson = (JSONArray) parser.parse(new FileReader(path + "departments.json"));

        Service testZadanie = new Service();


        testZadanie.ListInfoWellPararmeters(wellsParamListJson);
        testZadanie.ListInfoWell(wellsListJson);
        testZadanie.ListInfoDepartament(departListJson);

        testZadanie.ZapisVFileTask1(testZadanie.Zadanie1());
        testZadanie.ZapisVFileTask2(testZadanie.Zadanie2());
        testZadanie.ZapisVFileTask3(testZadanie.Zadanie3());

    }
}