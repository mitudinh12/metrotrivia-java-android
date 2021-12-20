package fi.tudinhmetropolia.metrotrivia;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static fi.tudinhmetropolia.metrotrivia.ChoseModeActivity.TAG;

public class Util {

    public static List<QuestionDataModel> convertJsonFileToModel(Context context, String fileName) {
        //Declare new QuestionModelList to make sure that function always return a list
        List<QuestionDataModel> questionModelList = new ArrayList<>();
        try {
            //Read assets file as string, (lines 70-80) - https://stackoverflow.com/questions/16110002/read-assets-file-as-string/23952928
            StringBuilder stringBuilder = new StringBuilder();
            InputStream inputStream = context.getAssets().open(fileName); //return byte based stream of data
            String jsonString;
            // use InputStreamReader to turn byte-based data to character-based data, then use BufferedReader to read text from a character-input stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //Read each line of text in json file, concatenate to json string
            while ((jsonString = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonString);
            }
            bufferedReader.close();
            Log.d(TAG, "" + stringBuilder.toString());
            // Turn json string to json array object using POJO class, explanation of this deserialization at: https://stackoverflow.com/questions/5554217/google-gson-deserialize-listclass-object-generic-type
            Type listType = new TypeToken<ArrayList<QuestionDataModel>>() {
            }.getType();
            questionModelList = new Gson().fromJson(stringBuilder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionModelList;
    }
}
