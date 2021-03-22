package main.service;

import main.config.Config;
import main.models.Departaments;
import main.models.WellParameters;
import main.models.Wells;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Service {
    public Service() throws IOException, ParseException {
    }

    JSONParser parser = new JSONParser();
    Config config = new Config();
    JSONObject jsonObject = new JSONObject();


    JSONArray wellsParamListJson = (JSONArray) parser.parse(new FileReader("C:/Users/SERGEY/IdeaProjects/TestZadnieB-1336/uploads/wellParameters.json"));
    JSONArray wellsListJson = (JSONArray) parser.parse(new FileReader("C:/Users/SERGEY/IdeaProjects/TestZadnieB-1336/uploads/wells.json"));
    JSONArray departListJson = (JSONArray) parser.parse(new FileReader("C:/Users/SERGEY/IdeaProjects/TestZadnieB-1336/uploads/departments.json"));

    ArrayList<WellParameters> wellParameters = new ArrayList<>(wellsParamListJson.size());
    ArrayList<Wells> wells = new ArrayList<>(wellsListJson.size());
    ArrayList<Departaments> departaments = new ArrayList<>(departListJson.size());

    public void ListInfoWellPararmeters(JSONArray wellsParamListJson) {
        for (Object wellsParamInfo : wellsParamListJson) {
            JSONObject obj = (JSONObject) wellsParamInfo;
            String parameterName = (String) obj.get("ParameterName");
            Long wellId = (Long) obj.get("WellId");
            Double value = (Double) obj.get("Value");

            WellParameters wellParametersObject = new WellParameters();
            wellParametersObject.setParameterName(parameterName);
            wellParametersObject.setWellId(wellId);
            wellParametersObject.setValue(value);
            wellParameters.add(wellParametersObject);
        }
    }

    public void ListInfoWell(JSONArray wellsListJson) {
        for (Object wellsAdd : wellsListJson) {
            JSONObject obj = (JSONObject) wellsAdd;
            Long Id = (Long) obj.get("Id");
            String Name = (String) obj.get("Name");
            Double X = (Double) obj.get("X");
            Double Y = (Double) obj.get("Y");

            Wells wellsObject = new Wells();
            wellsObject.setId(Id);
            wellsObject.setName(Name);
            if (X != null) {
                wellsObject.setX(X);
            }
            if (Y != null) {
                wellsObject.setY(Y);
            }
            wells.add(wellsObject);

        }
    }

    public void ListInfoDepartament(JSONArray departListJson) {
        for (Object departAdd : departListJson) {
            JSONObject obj = (JSONObject) departAdd;
            String depatamentName = (String) obj.get("Name");
            Double X = (Double) obj.get("X");
            Double Y = (Double) obj.get("Y");
            Double radius = (Double) obj.get("Radius");

            Departaments departamentsObject = new Departaments();
            departamentsObject.setNameDepartament(depatamentName);
            if (X != null) {
                departamentsObject.setxO(X + radius);
            }
            if (Y != null) {
                departamentsObject.setyO(Y + radius);
            }
            departamentsObject.setRadius(radius);

            departaments.add(departamentsObject);
        }
    }

    public ArrayList Zadanie1() {
        ArrayList arrayList = new ArrayList();
        System.out.println();
        arrayList.add("Параметры:");
        System.out.println("Параметры:");
        List<String> paramNameslist = wellParameters.stream().map(WellParameters::getParameterName).distinct().collect(Collectors.toList());
        for (int i = 0; i < 10; i++) {
            System.out.println(i + 1 + ". " + paramNameslist.get(i));
            arrayList.add(i + 1 + ". " + paramNameslist.get(i));
        }
        return arrayList;
    }

    public ArrayList Zadanie2() {
        ArrayList arrayList = new ArrayList();
        System.out.println();
        for (Wells wells1 : this.wells) {
            int x = wells1.getId().intValue();
            if (10 <= x && x <= 30) {
                System.out.println(wells1.getName());

                List<String> collectParamList = wellParameters.stream()
                        .filter(o -> o.getWellId() == x).map(WellParameters::getParameterName)
                        .distinct().collect(Collectors.toList());
                collectParamList.stream().forEach(name -> {
                            double[] collectValue = wellParameters.stream()
                                    .filter(o -> o.getParameterName().equals(name)).mapToDouble(WellParameters::getValue).toArray();
                            String min = new DecimalFormat("#0.00").format(DoubleStream.of(collectValue).min().getAsDouble());
                            String max = new DecimalFormat("#0.00").format(DoubleStream.of(collectValue).max().getAsDouble());
                            String avg = new DecimalFormat("#0.00").format(DoubleStream.of(collectValue).sum() / collectValue.length);
                            String median = new DecimalFormat("#0.00").format(collectValue[collectValue.length / 2]);
                            System.out.println(name + ": " + "min - " + min + " max - " + max + " ave - " + avg + " med - " + median);
                            arrayList.add(name + ": " + "min - " + min + " max - " + max + " ave - " + avg + " med - " + median);
                        }
                );

            }

        }
        return arrayList;
    }

    public ArrayList Zadanie3() {
        ArrayList arrayList = new ArrayList();
        ArrayList<String> unknownWells = new ArrayList<>();
        ArrayList<String> checkWell = new ArrayList<>();
        ArrayList<String> checkDepartament = new ArrayList<>();
        System.out.println();
        departaments.stream()
                .forEach(departaments1 -> {
                    arrayList.add(departaments1.getNameDepartament() + ": ");
                    System.out.print(departaments1.getNameDepartament() + ": ");
                    wells.stream()
                            .forEach(wells1 -> {
                                try {
                                    if (Math.pow((wells1.getX() - departaments1.getxO()), 2)
                                            + Math.pow((wells1.getY() - departaments1.getyO()), 2) <= Math.pow(departaments1.getRadius(), 2)) {
                                        checkWell.add(wells1.getName());
                                        checkDepartament.add(wells1.getName());
                                        arrayList.add(wells1.getName() + ", ");
                                        System.out.print(wells1.getName() + ", ");
                                    } else if (!unknownWells.contains(wells1.getName()) && !checkWell.contains(wells1.getName())) {
                                        unknownWells.add(wells1.getName());

                                    }
                                    if (checkWell.contains(wells1.getName())) {
                                        unknownWells.remove(wells1.getName());
                                    }
                                } catch (NullPointerException e) {
                                    unknownWells.add(wells1.getName());
                                }
                            });
                    if (checkDepartament.isEmpty()) {
                        arrayList.add(departaments1.getNameDepartament() + ": скважины отсутстуют.");
                        System.out.println(departaments1.getNameDepartament() + ": скважины отсутстуют.");
                    }
                    System.out.println();
                });
        System.out.println();
        /*Выбирайте какой удобней, сначала сделал так:
         System.out.println("Неизвестное месторождение: " + wellWithoutCords)*/
        System.out.print("Неизвестное месторождение: ");
        for (int i = 0; i < unknownWells.size(); i++) {
            arrayList.add(unknownWells.get(i) + ", ");
            System.out.print(unknownWells.get(i) + ", ");
        }
        return arrayList;
    }

    public void ZapisVFileTask1(ArrayList wellParametersArray) throws IOException, ParseException {
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(wellParametersArray);
        jsonObject.put("", jsonArray);

        FileWriter fileWellParam = new FileWriter(config.uploadPath() + "Task1Test.json");
        fileWellParam.write(jsonObject.toJSONString());
        fileWellParam.flush();
        fileWellParam.close();
    }

    public void ZapisVFileTask2(ArrayList wellsArray) throws IOException, ParseException {
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(wellsArray);
        jsonObject.put("", jsonArray);

        FileWriter fileWellParam = new FileWriter(config.uploadPath() + "Task2Test.json");
        fileWellParam.write(jsonObject.toJSONString());
        fileWellParam.flush();
        fileWellParam.close();
    }

    public void ZapisVFileTask3(ArrayList departamentsArray) throws IOException, ParseException {
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(departamentsArray);
        jsonObject.put("", jsonArray);

        FileWriter fileWellParam = new FileWriter(config.uploadPath() + "Task3Test.json");
        fileWellParam.write(jsonObject.toJSONString());
        fileWellParam.flush();
        fileWellParam.close();
    }

}


