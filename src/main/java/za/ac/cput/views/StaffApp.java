package za.ac.cput.views;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.entity.Staff;

import java.io.IOException;


public class StaffApp {

   private static OkHttpClient client = new OkHttpClient();
    private  static String run(String URL ) throws IOException {
        Request request = new Request.Builder()
                .url(URL)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();

        }
    }
    public static void getAll(){
        try{
            final String URL = "http://localhost:8080/staff/getall";
            String responseBody = run(URL);
            JSONArray staffs = new JSONArray(responseBody);

            for (int i=0; i<staffs.length();i++){
                JSONObject staff =  staffs.getJSONObject(i);

                Gson g = new Gson();
                Staff s = g.fromJson(staff.toString(), Staff.class);
                System.out.println(s.toString());
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void main (String [] args){
        getAll();
    }
}


