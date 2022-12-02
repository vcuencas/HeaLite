//package com.example.healite;
//
/////**
//// * Sample Java code for youtube.search.list
//// * See instructions for running these code samples locally:
//// * https://developers.google.com/explorer-help/code-samples#java
//// */
////
////import com.google.api.client.auth.oauth2.Credential;
////import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
////import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
////import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
////import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
////import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
////import com.google.api.client.googleapis.json.GoogleJsonResponseException;
////import com.google.api.client.http.javanet.NetHttpTransport;
////import com.google.api.client.json.JsonFactory;
////import com.google.api.client.json.jackson2.JacksonFactory;
////
////import com.google.api.services.youtube.YouTube;
////import com.google.api.services.youtube.model.SearchListResponse;
////
////import java.io.IOException;
////import java.io.InputStream;
////import java.io.InputStreamReader;
////import java.security.GeneralSecurityException;
////import java.util.Arrays;
////import java.util.Collection;
////
////public class ApiExample {
////    private static final String CLIENT_SECRETS= "client_secret.json";
////    private static final Collection<String> SCOPES =
////            Arrays.asList("https://www.googleapis.com/auth/youtube.force-ssl");
////
////    private static final String APPLICATION_NAME = "API code samples";
////    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
////
////    /**
////     * Create an authorized Credential object.
////     *
////     * @return an authorized Credential object.
////     * @throws IOException
////     */
////    public static Credential authorize(final NetHttpTransport httpTransport) throws IOException {
////        // Load client secrets.
////        InputStream in = ApiExample.class.getResourceAsStream(CLIENT_SECRETS);
////        GoogleClientSecrets clientSecrets =
////                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
////        // Build flow and trigger user authorization request.
////        GoogleAuthorizationCodeFlow flow =
////                new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
////                        .build();
////        Credential credential =
////                new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
////        return credential;
////    }
////
////    /**
////     * Build and return an authorized API client service.
////     *
////     * @return an authorized API client service
////     * @throws GeneralSecurityException, IOException
////     */
////    public static YouTube getService() throws GeneralSecurityException, IOException {
////        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
////        Credential credential = authorize(httpTransport);
////        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
////                .setApplicationName(APPLICATION_NAME)
////                .build();
////    }
////
////    /**
////     * Call function to create API service object. Define and
////     * execute API request. Print API response.
////     *
////     * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
////     */
////    public static void main(String[] args)
////            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
////        YouTube youtubeService = getService();
////        // Define and execute the API request
////        YouTube.Search.List request = youtubeService.search()
////                .list("snippet");
////        SearchListResponse response = request.execute();
////        System.out.println(response);
////    }
////}
//
//import com.google.api.client.googleapis.json.GoogleJsonResponseException;
//import com.google.api.client.http.HttpRequest;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
////import com.google.api.services.samples.youtube.cmdline.Auth;
//import com.google.api.services.youtube.YouTube;
//import com.google.api.services.youtube.model.ResourceId;
//import com.google.api.services.youtube.model.SearchListResponse;
//import com.google.api.services.youtube.model.SearchResult;
//import com.google.api.services.youtube.model.Thumbnail;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Properties;
//
///**
// * Print a list of videos matching a search term.
// *
// * @author Jeremy Walker
// */
//
///**
// * Found on: https://stackoverflow.com/questions/33263394/how-to-search-youtube-video-in-android-by-specific-keyword
// * */
//public class Search {
//
//    /**
//     * Define a global variable that identifies the name of a file that
//     * contains the developer's API key.
//     */
//    private static final String PROPERTIES_FILENAME = "youtube.properties";
//
//    private static final long NUMBER_OF_VIDEOS_RETURNED = 1;
//
//    private static List<SearchResult> searchResultList;
//
//    /**
//     * Define a global instance of a Youtube object, which will be used
//     * to make YouTube Data API requests.
//     */
//    private static YouTube youtube;
//
//    /**
//     * Initialize a YouTube object to search for videos on YouTube. Then
//     * display the name and thumbnail image of each video in the result set.
//     *
//     * @param args command line args.
//     */
//    public static void main(String[] args) {
//        // Read the developer key from the properties file.
//        Properties properties = new Properties();
//        try {
//            InputStream in = Search.class.getResourceAsStream("/app/assets/" + PROPERTIES_FILENAME);
//            properties.load(in);
//
//        } catch (IOException e) {
//            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
//                    + " : " + e.getMessage());
//            System.exit(1);
//        }
//
//        try {
//            // This object is used to make YouTube Data API requests. The last
//            // argument is required, but since we don't need anything
//            // initialized when the HttpRequest is initialized, we override
//            // the interface and provide a no-op function.
//            youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
//                public void initialize(HttpRequest request) throws IOException {
//                }
//            }).setApplicationName("youtube-cmdline-search-sample").build();
//
//            // Prompt the user to enter a query term.
//            String queryTerm = getInputQuery();
//
//            // Define the API request for retrieving search results.
//            YouTube.Search.List search = youtube.search().list("id,snippet");
//
//            // Set your developer key from the Google Developers Console for
//            // non-authenticated requests. See:
//            // https://console.developers.google.com/
//            String apiKey = properties.getProperty("youtube.apikey");
//            search.setKey(apiKey);
//            search.setQ(queryTerm);
//
//            // Restrict the search results to only include videos. See:
//            // https://developers.google.com/youtube/v3/docs/search/list#type
//            search.setType("video");
//
//            // To increase efficiency, only retrieve the fields that the
//            // application uses.
//            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
//            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
//
//            // Call the API and print results.
//            SearchListResponse searchResponse = search.execute();
////            List<SearchResult> searchResultList = searchResponse.getItems();
//            searchResultList = searchResponse.getItems();
//            if (searchResultList != null) {
//                prettyPrint(searchResultList.iterator(), queryTerm);
//            }
//        } catch (GoogleJsonResponseException e) {
//            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
//                    + e.getDetails().getMessage());
//        } catch (IOException e) {
//            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }
//
//    public static List<SearchResult> getSearchResultList() {
//        return searchResultList;
//    }
//
//    /*
//     * Prompt the user to enter a query term and return the user-specified term.
//     */
//    private static String getInputQuery() throws IOException {
//
//        String inputQuery = "calm music";
//
////        System.out.print("Please enter a search term: ");
////        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
////        inputQuery = bReader.readLine();
//
//        if (inputQuery.length() < 1) {
//            // Use the string "YouTube Developers Live" as a default.
//            inputQuery = "YouTube Developers Live";
//        }
//        return inputQuery;
//    }
//
//    /*
//     * Prints out all results in the Iterator. For each result, print the
//     * title, video ID, and thumbnail.
//     *
//     * @param iteratorSearchResults Iterator of SearchResults to print
//     *
//     * @param query Search query (String)
//     */
//    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {
//
//        System.out.println("\n=============================================================");
//        System.out.println(
//                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
//        System.out.println("=============================================================\n");
//
//        if (!iteratorSearchResults.hasNext()) {
//            System.out.println(" There aren't any results for your query.");
//        }
//
//        while (iteratorSearchResults.hasNext()) {
//
//            SearchResult singleVideo = iteratorSearchResults.next();
//            ResourceId rId = singleVideo.getId();
//
//            // Confirm that the result represents a video. Otherwise, the
//            // item will not contain a video ID.
//            if (rId.getKind().equals("youtube#video")) {
//                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
//
//                System.out.println(" Video Id" + rId.getVideoId());
//                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
//                System.out.println(" Thumbnail: " + thumbnail.getUrl());
//                System.out.println("\n-------------------------------------------------------------\n");
//            }
//        }
//    }
//}