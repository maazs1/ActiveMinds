package com.example.activemindsapp;



import java.util.HashMap;
import java.util.Map;

    /**
     * This is a class for the Emoticon used displaying the cow caricature to depict the mood of User
     */
    public class Emoticon{

        private String emotionalState;
        private int imageLink;
        private HashMap<String, Integer> emoticonData = new HashMap<>();

        /**
         * This populates the hashmap with keys (emotional state) and values (image of cow)
         * @param version
         * This determines which version of cow image to use. currently there are 2 for each emotional state
         **/
        private void populateEmoticonData(int version){
            if(version == 1){
                emoticonData.put("HAPPY", R.drawable.mooood_logo);
                emoticonData.put("SAD", R.drawable.circle_sad);
                emoticonData.put("LAUGHING", R.drawable.laughing_cow_v1);
                emoticonData.put("IN LOVE", R.drawable.circle_inlove);
                emoticonData.put("ANGRY", R.drawable.angry_cow_v1);
                //emoticonData.put("SICK", R.drawable.sick_cow_v1);
                emoticonData.put("AFRAID", R.drawable.cricle_afraid);

            } else if(version == 2){
                emoticonData.put("HAPPY", R.drawable.happy_cow_v2);
                emoticonData.put("SAD", R.drawable.circle_sad);
                emoticonData.put("LAUGHING", R.drawable.laughing_cow_v2);
                emoticonData.put("IN LOVE", R.drawable.circle_inlove);
                emoticonData.put("ANGRY", R.drawable.angry_cow_v2);
                //emoticonData.put("SICK", R.drawable.sick_cow_v2);
                emoticonData.put("AFRAID", R.drawable.cricle_afraid);
            }

            else if(version == 3){
                emoticonData.put("ANXIETY", R.drawable.circle_anxiety);
                emoticonData.put("DEPRESSION", R.drawable.circle_depression);
                emoticonData.put("STRESS", R.drawable.circle_stress);
                emoticonData.put("SCHIZOPHRENIA", R.drawable.circle_schiz);
                emoticonData.put("EATING DISORDERS", R.drawable.sick_cow_v2);
                emoticonData.put("GENDER AND SEXUAL DIVERSITY (LGBTQ2+)", R.drawable.afraid_cow_v2);
            }


        }

        /**
         * This is the constructor when you need emotionalState but only have imageLink
         **/
        public Emoticon( int imageLink, int version ){
            populateEmoticonData(version);

            this.imageLink = imageLink;
            for(Map.Entry<String, Integer> entry: emoticonData.entrySet()){
                if(this.imageLink == entry.getValue()){
                    this.emotionalState = entry.getKey();
                }
            }

        }

        /**
         *  This is the constructor when you need imageLink but only have emotionalState
         **/
        public Emoticon( String emotionalState, int version ){
            populateEmoticonData(version);

            this.emotionalState = emotionalState;
            this.imageLink = emoticonData.get(emotionalState);
        }

        /**
         * Simple getters and setters
         **/
        public String getEmotionalState() {
            return this.emotionalState;
        }

        public void setEmotionalState(String emotionalState) {
            this.emotionalState = emotionalState;
        }

        public int getImageLink() {
            return this.imageLink;
        }

        public void setImageLink(int imageLink) {
            this.imageLink = imageLink;
        }
    }


