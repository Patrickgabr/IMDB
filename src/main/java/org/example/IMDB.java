package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.example.User.Information;
import org.example.User;
import org.example.Rating;
import org.example.Production;
public class IMDB
{
    private static IMDB mInstance;

    private List<User> mUsers;
    private List<Actor> mActors;
    private List<Request> mRequests;
    private List<Production> mProductions;

    // constructor private
    private IMDB()
    {
        mUsers = new ArrayList<User>();
        mActors = new ArrayList<Actor>();
        mRequests = new ArrayList<Request>();
        mProductions = new ArrayList<>();
    }

    public static IMDB getInstance()
    {
        if (mInstance == null)
            mInstance = new IMDB();
        return mInstance;
    }

    public void run()
    {
        parseActors("actors.json");
        parseUsers("accounts.json");
        parseRequests("requests.json");
        parseProductions("production.json");

        printInterfaceModeMenu();
        Scanner scanner = new Scanner(System.in);
        int graphicOption = 0;
        if (scanner.hasNextInt())
            graphicOption = scanner.nextInt();

        if (graphicOption == 1)
        {
            User u;
            while (true) {
                Credentials c = printLogin();
                u = authenticateUser(c);
                if (u != null)
                    break;
            }

            UserMenu UM = new UserMenu();
            UM.generateUserMenu(u.getType());
        }
    }

    private Credentials printLogin()
    {
        Credentials ret;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome back! Enter your credentials!\n");

        // ca sa nu poate da mail sau pass gol sa apese direct enter
        String email;
        do {
            System.out.print("\temail: ");
            email = scanner.nextLine().trim();
        } while (email.isEmpty());

        String passw;
        do {
            System.out.print("\tpassword: ");
            passw = scanner.nextLine().trim();
        } while (passw.isEmpty());

        ret = new Credentials(email, passw);
        return ret;
    }

    private User authenticateUser(Credentials c) {
        //System.out.println("Am " + mUsers.size() + " utilizatori");
        for (User u : mUsers)
            if (u.getCredentials().equals(c))
                return u;

        return null;
    }

    private void printInterfaceModeMenu()
    {
        System.out.println("Welcome to IMDB !\n");
        System.out.println("Choose action:\n");
        System.out.println("\t1) Command line interface");
        System.out.println("\t2) Graphical user interface");
    }

    private void parseActors(String filename)
    {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONArray actorsArray = (JSONArray) obj;

            for (Object act : actorsArray) {
                JSONObject actJson = (JSONObject)act;

                // iau campurile - intai cele simple
                String name = (String)actJson.get("name");
                String biography = (String)actJson.get("biography");

                JSONArray performances = (JSONArray)actJson.get("performances");
                List<Performance> actorPerformances = new ArrayList<Performance>();

                for (Object perf : performances) {
                    JSONObject perfJson = (JSONObject)perf;
                    String title = (String)perfJson.get("title");
                    String type = (String)perfJson.get("type");

                    // Create Performance object and add to the list
                    Performance performance = new Performance(title, type);
                    actorPerformances.add(performance);
                }

                // Create Actor object and add to the list
                Actor actor = new Actor(name, biography, actorPerformances);
                mActors.add(actor);
            } // for pe JSONArray mare
        } catch (IOException e) {
            System.out.println("Probleme la cititre din actors.json");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Probleme la parsare din actors.json");
            e.printStackTrace();
        }
    }

    private void parseUsers(String filename) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filename)) {
            JSONArray acounts = (JSONArray) parser.parse(reader);

            for (Object obj : acounts) {
                JSONObject accountObj = (JSONObject) obj;
                String username = (String) accountObj.get("username");
                String experienceStr = (String)accountObj.get("experience");
                int experience;
                if (experienceStr != null)
                    experience = Integer.parseInt(experienceStr);
                else
                    experience = Integer.MAX_VALUE;

                // partea cu information object
                JSONObject informationObject = (JSONObject) accountObj.get("information");
                JSONObject credentialsObject = (JSONObject) informationObject.get("credentials");
                String email = (String) credentialsObject.get("email");
                String password = (String) credentialsObject.get("password");
                String name = (String) informationObject.get("name");
                String country = (String) informationObject.get("country");
                int age = ((Long) informationObject.get("age")).intValue();
                //String ageString = (String)informationObject.get("age"); DE SCOS dc merge
                //Integer age = Integer.parseInt(ageString);
                String genderString = (String) informationObject.get("gender");
                char gender = genderString.charAt(0);
                String birthDate = (String) informationObject.get("birthDate");

                // facem un nou obiect user - vedem exact ce clasa - DE ARANJAT CLASA SI CONSTRUCTORUL
                User.Information information = new User.Information.Builder()
                        .email(email)
                        .password(password)
                        .name(name)
                        .country(country)
                        .age(age)
                        .gender(gender)
                        .birthDate(birthDate)
                        .build();

                String userType = (String) accountObj.get("userType");
                AccountType accountType;

                accountType = AccountType.valueOf(userType.toUpperCase());

                User u = UserFactory.createUser(accountType, username,
                        experience, information);

                if (userType == "Contributor") {
                    JSONArray productionsContribution = (JSONArray) accountObj.get("productionsContribution");
                    JSONArray actorsContribution = (JSONArray) accountObj.get("actorsContribution");

                    List<String> productionsList = new ArrayList<String>();
                    List<String> actorsList = new ArrayList<String>();

                    for (Object production : productionsContribution)
                        productionsList.add((String) production);

                    for (Object actor : actorsContribution)
                        actorsList.add((String) actor);

                    // ? DACA NU LE BAG LA CONSTRUCTOR SAU BUILDER
                    ((Contributor)u).setProductionContributions(productionsList);
                    ((Contributor)u).setActorContributions(actorsList);
                }

                if (accountObj.containsKey("favoriteProductions")) {
                    JSONArray favoriteProductions = (JSONArray) accountObj.get("favoriteProductions");
                    List<String> favoritePsList = new ArrayList<String>();

                    for (Object production : favoriteProductions)
                        favoritePsList.add((String) production);

                    if (userType == "Contributor")
                        ((Contributor)u).setFavoriteProductions(favoritePsList);
                    else if (userType == "Regular")
                        ((Regular)u).setFavoriteProductions(favoritePsList);
                }

                if (accountObj.containsKey("favoriteActors")) {
                    JSONArray favoriteActors = (JSONArray) accountObj.get("favoriteActors");
                    List<String> favoriteAsList = new ArrayList<String>();

                    for (Object actor : favoriteActors)
                        favoriteAsList.add((String)actor);

                    if (userType == "Contributor")
                        ((Contributor)u).setFavoriteActors(favoriteAsList);
                    else if (userType == "Regular")
                        ((Regular)u).setFavoriteActors(favoriteAsList);
                }

                mUsers.add(u);
            }
        } catch (IOException e) {
            System.out.println("Probleme la cititre din accounts.json");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Probleme la parsare din accounts.json");
            e.printStackTrace();
        }
    } // parseActors

    private void parseRequests(String filename) {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(filename)) {
            JSONArray requestsArray = (JSONArray) parser.parse(reader);

            for (Object obj : requestsArray) {
                JSONObject requestObj = (JSONObject) obj;

                RequestType type = RequestType.valueOf((String) requestObj.get("type"));
                //DateTimeFormatter createDate = (DateTimeFormatter)requestObj.get("createdDate");
                String createDate = (String)requestObj.get("createdDate");
                String username = (String)requestObj.get("username");

                String title = "";
                if (requestObj.containsKey("actorName"))
                    title = (String) requestObj.get("actorName");
                else if (requestObj.containsKey("movieTitle"))
                    title = (String) requestObj.get("movieTitle");

                String to = (String)requestObj.get("to");// POATE ENUM
                String description = (String)requestObj.get("description");

                // ? createdDate
                Request r = new Request(type, title, description, username, to);
                mRequests.add(r);
            }
        } catch (IOException e) {
            System.out.println("Probleme la cititre din requests.json");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Probleme la parsare din requests.json");
            e.printStackTrace();
        }
    }

    private void parseProductions(String filename) {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filename)) {
            JSONArray productionArray = (JSONArray) parser.parse(reader);

            for (Object obj : productionArray) {
                JSONObject productionObj = (JSONObject) obj;

                String title = (String) productionObj.get("title");
                String type = (String) productionObj.get("type");

                JSONArray directors = (JSONArray) productionObj.get("directors");
                List<String> directorsList = new ArrayList<String>(directors);
                JSONArray actors = (JSONArray) productionObj.get("actors");
                List<String> actorsList = new ArrayList<String>(actors);
                JSONArray genres = (JSONArray) productionObj.get("genres");
                List<Genre> genresList = new ArrayList<Genre>(genres);

                JSONArray ratings = (JSONArray) productionObj.get("ratings");
                List<Rating> ratingsList = new ArrayList<Rating>();
                for (Object rating : ratings) {
                    JSONObject ratingObj = (JSONObject) rating;
                    String username = (String) ratingObj.get("username");
                    int val = ((Long) ratingObj.get("rating")).intValue();
                    String comment = (String) ratingObj.get("comment");

                    Rating r = new Rating(username, val, comment);
                    ratingsList.add(r);
                }

                String plot = (String) productionObj.get("plot");
                double averageRating = (double)productionObj.get("averageRating");
                //System.out.println("AverageRating este " + averageRating);
                int releaseYear = ((Long) productionObj.getOrDefault("releaseYear", 0L)).intValue();

                if (type.equals("Movie")) {
                    String duration = (String)productionObj.get("duration");
                    Movie m = new Movie(title, plot, directorsList, actorsList,
                            genresList, ratingsList, averageRating,
                            duration, releaseYear);

                    mProductions.add(m);
                }
                else {
                    //int numSeasons = Integer.parseInt((String) productionObj.get("numSeasons"));
                    int numSeasons = ((Long) productionObj.get("numSeasons")).intValue();
                    Map<String, List<Episode>> seasonsMap = new HashMap<String, List<Episode>>();

                    JSONObject seasonsObj = (JSONObject) productionObj.get("seasons");
                    for (int i = 1; i <= numSeasons; i++) {
                        String season = "Season " + i;
                        JSONArray episodesArray = (JSONArray) seasonsObj.get(season);

                        List<Episode> episodes = new ArrayList<Episode>();
                        for (Object episodeObj : episodesArray) {
                            JSONObject epJson = (JSONObject) episodeObj;
                            String episodeName = (String) epJson.get("episodeName");
                            String episodeDuration = (String) epJson.get("duration");
                            episodes.add(new Episode(episodeName, episodeDuration));
                        }

                        seasonsMap.put(season, episodes);
                    }

                    Series s = new Series(title, plot, directorsList, actorsList,
                            genresList, ratingsList, averageRating,
                            releaseYear, numSeasons, seasonsMap);

                    mProductions.add(s);
                }

            }
        } catch (IOException e) {
            System.out.println("Probleme la cititre din requests.json");
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Probleme la parsare din requests.json");
            e.printStackTrace();
        }
    }
}