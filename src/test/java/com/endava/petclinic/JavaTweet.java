package com.endava.petclinic;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

    public class JavaTweet {

        static String consumerKeyStr = "xDpvjjIfui8Su72Ti1AR1CKzi";
        static String consumerSecretStr = "HobgaN3k3jn0vk11ItpV1CSWhyzpIm0Nf2FXbF01wZmDNxmyxl";
        static String accessTokenStr = "278136668-zJNcE6VzUubCamfnS29EEw1S0HyRUDufFl42AbUk";
        static String accessTokenSecretStr = "2Q5WhderaUy2Tk8tL7n400L4ddoXm6STWtasc6yKpM3Wf";

        public static void main(String[] args) {

            try {
                Twitter twitter = new TwitterFactory().getInstance();

                twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
                AccessToken accessToken = new AccessToken(accessTokenStr,
                        accessTokenSecretStr);

                twitter.setOAuthAccessToken(accessToken);

                twitter.updateStatus("Bogdan says hello!");

                System.out.println("Successfully updated the status in Twitter.");
            } catch (TwitterException te) {
                te.printStackTrace();
            }
        }

    }
