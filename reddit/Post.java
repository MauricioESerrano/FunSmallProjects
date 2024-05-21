/*
 * Name: Mauricio Serrano
 * Email: m2serrano.ucsd.edu
 * PID: A17020958
 * Sources used: "None" 
 * 
 * This code is 1/2 needed to make simplified reddit. it allows user to post, post comments to posts, upvote,downvote, replyto, and have a karma system. It is essentially a social media platform. 
 */

//this is the arraylist import needed to make this program work effectively
import java.util.ArrayList;

public class Post {
    
    //instance variables
    private String title;
    private String content;
    private Post replyTo;
    private User author;
    private int upvoteCount;
    private int downvoteCount;

    private static final String TO_STRING_POST_FORMAT = "[%d|%d]\t%s\n\t%s";
    private static final String TO_STRING_COMMENT_FORMAT = "[%d|%d]\t%s";


     /**
     * This method is for creating original psot
     * @param title title of post
     * @param content content of post
     *  @param author username of post
     * @return no return
     */ 
    public Post(String title, String content, User author) {

        //intialize instance variables for original post
        this.title = title; 
        this.content = content; 
        this.author = author;
        this.replyTo = null;

        upvoteCount = 1;
        downvoteCount = 0;
    }

    
     /**
     * This method is for creating a comment post
     * @param replyTo the post they will bereplying to
     * @param content content of post
     *  @param author username of post
     * @return no return
     */ 
    public Post(String content, Post replyTo, User author) {

        //intialize instance variables for comment post
        title = null; 

        this.content = content;
        this.replyTo = replyTo;
        this.author = author;
        
        upvoteCount = 1;
        downvoteCount = 0;

    }

     /**
     * This method is a getter for title
     * @return title
     */ 
    public String getTitle() {
        return this.title;

    }

     /**
     * This method is a getter for replyTo
     * @return replyto
     */ 
    public Post getReplyTo() {
        return this.replyTo;

    }

     /**
     * This method is a getter for author
     * @return author
     */ 
    public User getAuthor() {
        return this.author;
    }

     /**
     * This method is a getter for getupvotecount
     * @return upvotecount
     */ 
    public int getUpvoteCount() {
        return this.upvoteCount;

    }

     /**
     * This method is a getter downvotecount
     * @return downvotecount
     */ 
    public int getDownvoteCount() {
        return this.downvoteCount;

    }

    /**
     * This method updates posts upvote count 
     * @param isincrement boolean
     * @return no return
     */ 
    public void updateUpvoteCount(boolean isIncrement) {
        //IF true, add upvotecount 1
        if (isIncrement == true) {

            upvoteCount += 1;
        }
        //if false, remove 1 from upvotecount
        else if (isIncrement ==false)  {
            upvoteCount -= 1;
        }

    }

    /**
     * This method updates posts downvote count 
     * @param isincrement boolean
     * @return no return
     */ 
    public void updateDownvoteCount(boolean isIncrement) {
        //if true, add 1 to downvotecount
        if (isIncrement == true) {

            downvoteCount += 1;
        }
        //if false, remove 1 from downvotecount
        else if (isIncrement == false) {
            downvoteCount -= 1;
        }


    }

    /**
     * This method gets the thread of posts
     * @return arraylist of thread of posts
     */ 
    public ArrayList<Post> getThread() {

        //creates toreturn arraylist
        ArrayList<Post> toReturn = new ArrayList<>(); 

        //local variables
        Post currPost = this; 
        while (currPost != null) {
             
             //if getthread is for og post, add it to list and break
              if(currPost.replyTo == null){
                toReturn.add(0, currPost);
                break;
              }

              //add currentpost to index 0 and set currentpost to currpost.reply to to update it
              toReturn.add(0, currPost);
              currPost = currPost.replyTo;
        }
        //return the arraylist
        return toReturn;

    }

    /**
     * This method is the format that is presented to user regarding upvotecount and downvotecount of each post. 
     * @return returns upvote count, downvote count, title and content for both posts and comments
     */ 
    public String toString() {

        //if post is original post
        if (title != null && replyTo == null) {

            return String.format(TO_STRING_POST_FORMAT, getUpvoteCount(), getDownvoteCount(),getTitle(), this.content);
        
        }

        //if post is comment.
        else { 

            return String.format(TO_STRING_COMMENT_FORMAT, getUpvoteCount(), getDownvoteCount(), this.content);

        }
 
    }
}

 