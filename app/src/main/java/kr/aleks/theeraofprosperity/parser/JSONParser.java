package kr.aleks.theeraofprosperity.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.aleks.theeraofprosperity.data.AboutBuildings;

public class JSONParser {

    public List parse(JSONObject jsonObject) {
        List postList = new ArrayList();
        AboutBuildings postValue;
        try {
            JSONArray postsArray = jsonObject.getJSONArray("posts");
            for (int i = 0; i < postsArray.length(); i++) {
                JSONObject posts = postsArray.getJSONObject(i);
                JSONObject post = posts.getJSONObject("post");

                postValue = new AboutBuildings();

                String title = post.getString("title");
                int image = post.getInt("images");

                postValue.setTitle(title);
                postValue.setImage(image);
                postList.add(postValue);
            }
        } catch (JSONException err) {
            err.printStackTrace();
        }
        return postList;
    }
}
