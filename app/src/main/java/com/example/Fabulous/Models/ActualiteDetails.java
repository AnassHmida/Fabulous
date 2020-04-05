package com.example.Fabulous.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class ActualiteDetails {


    @Expose
    @SerializedName("posts")
    public List<PostsEntity> posts;
    @Expose
    @SerializedName("total")
    public int total;

    public static class PostsEntity {
        @Expose
        @SerializedName("liked")
        public boolean liked;
        @Expose
        @SerializedName("likes")
        public int likes;
        @Expose
        @SerializedName("pictureURL")
        public String pictureurl;
        @Expose
        @SerializedName("Training")
        public TrainingEntity training;
        @Expose
        @SerializedName("Creator")
        public CreatorEntity creator;
        @Expose
        @SerializedName("Picture")
        public PictureEntity picture;
        @Expose
        @SerializedName("trainingId")
        public int trainingid;
        @Expose
        @SerializedName("isVisible")
        public boolean isvisible;
        @Expose
        @SerializedName("accepted")
        public boolean accepted;
        @Expose
        @SerializedName("pictureId")
        public int pictureid;
        @Expose
        @SerializedName("createdAt")
        public String createdat;
        @Expose
        @SerializedName("description")
        public String description;
        @Expose
        @SerializedName("userId")
        public int userid;
        @Expose
        @SerializedName("postId")
        public int postid;

        public PostsEntity(String description) {
            this.description = description;
        }
    }

    public static class TrainingEntity {
        @Expose
        @SerializedName("Purchase")
        public PurchaseEntity purchase;
        @Expose
        @SerializedName("consentEnsurement")
        public String consentensurement;
        @Expose
        @SerializedName("consentQuestionHeader")
        public String consentquestionheader;
        @Expose
        @SerializedName("virtualParticipantCounts")
        public int virtualparticipantcounts;
        @Expose
        @SerializedName("consentExplication")
        public String consentexplication;
        @Expose
        @SerializedName("language")
        public int language;
        @Expose
        @SerializedName("descriptionVideoId")
        public String descriptionvideoid;
        @Expose
        @SerializedName("createdAt")
        public String createdat;
        @Expose
        @SerializedName("detailsFileId")
        public int detailsfileid;
        @Expose
        @SerializedName("coverId")
        public int coverid;
        @Expose
        @SerializedName("description")
        public String description;
        @Expose
        @SerializedName("price")
        public int price;
        @Expose
        @SerializedName("name")
        public String name;
        @Expose
        @SerializedName("trainingId")
        public int trainingid;
    }

    public static class PurchaseEntity {
        @Expose
        @SerializedName("Grade")
        public GradeEntity grade;
        @Expose
        @SerializedName("trainingid")
        public int trainingid;
        @Expose
        @SerializedName("userid")
        public int userid;
        @Expose
        @SerializedName("gradeId")
        public int gradeid;
        @Expose
        @SerializedName("purchasedAt")
        public String purchasedat;
        @Expose
        @SerializedName("trainingId")
        public int purtrainingid;
        @Expose
        @SerializedName("userId")
        public int puruserid;
        @Expose
        @SerializedName("purchasedTrainingId")
        public int purchasedtrainingid;
    }

    public static class GradeEntity {
        @Expose
        @SerializedName("pictureURL")
        public String pictureurl;
        @Expose
        @SerializedName("Picture")
        public PictureEntity picture;
        @Expose
        @SerializedName("pictureId")
        public int pictureid;
        @Expose
        @SerializedName("title")
        public String title;
        @Expose
        @SerializedName("gradeId")
        public int gradeid;
    }

    public static class PictureEntity {
        @Expose
        @SerializedName("container")
        public String container;
        @Expose
        @SerializedName("type")
        public String type;
        @Expose
        @SerializedName("name")
        public String name;
        @Expose
        @SerializedName("assetId")
        public int assetid;
    }

    public static class CreatorEntity {
        @Expose
        @SerializedName("avatarURL")
        public String avatarurl;
        @Expose
        @SerializedName("Avatar")
        public AvatarEntity avatar;
        @Expose
        @SerializedName("createPostAuthorized")
        public boolean createpostauthorized;
        @Expose
        @SerializedName("forumAccessAuthorized")
        public boolean forumaccessauthorized;
        @Expose
        @SerializedName("currentLanguage")
        public int currentlanguage;
        @Expose
        @SerializedName("postalCode")
        public String postalcode;
        @Expose
        @SerializedName("city")
        public String city;
        @Expose
        @SerializedName("sponsorshipBalance")
        public int sponsorshipbalance;
        @Expose
        @SerializedName("phoneNumber")
        public String phonenumber;
        @Expose
        @SerializedName("providerAvatarURL")
        public String provideravatarurl;
        @Expose
        @SerializedName("avatarId")
        public int avatarid;
        @Expose
        @SerializedName("code")
        public String code;
        @Expose
        @SerializedName("name")
        public String name;
        @Expose
        @SerializedName("userId")
        public int userid;
    }

    public static class AvatarEntity {
        @Expose
        @SerializedName("container")
        public String container;
        @Expose
        @SerializedName("type")
        public String type;
        @Expose
        @SerializedName("name")
        public String name;
        @Expose
        @SerializedName("assetId")
        public int assetid;
    }

    public static class PictureEntity2 {
        @Expose
        @SerializedName("container")
        public String container;
        @Expose
        @SerializedName("type")
        public String type;
        @Expose
        @SerializedName("name")
        public String name;
        @Expose
        @SerializedName("assetId")
        public int assetid;
    }

    public ActualiteDetails(String description) {
        PostsEntity NewPost = new PostsEntity(description);
        ArrayList<PostsEntity> NewPostList = new ArrayList<PostsEntity>();
        NewPostList.add(NewPost);
        this.posts = NewPostList;
    }

    @Override
    public String toString() {
        return "ActualiteDetails{" +
                "posts=" + posts +
                ", total=" + total +
                '}';
    }
}
