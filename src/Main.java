import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        String profile = "{\"username\": \"test\", \"password\": passwd}";
        User sampleUser = gson.fromJson(profile, User.class);
        System.out.println(sampleUser);
        User anotherUser = new User("test1", "password");
        String anotherProfile = gson.toJson(anotherUser);
        System.out.println(anotherProfile);

        ArrayList<User> users = new ArrayList<>();
        users.add(sampleUser);
        users.add(anotherUser);
        String usersJson = gson.toJson(users);
        System.out.println(usersJson);

        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> anotherList = gson.fromJson(usersJson, listType);
        for (User user : anotherList) {
            System.out.println(user);
        }
    }
}