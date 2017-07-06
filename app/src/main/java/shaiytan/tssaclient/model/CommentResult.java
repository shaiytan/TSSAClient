package shaiytan.tssaclient.model;

/**
 * Created by Shaiytan on 06.07.2017.
 */

class CommentResult {
    private int review_id;

    public CommentResult(int review_id) {
        this.review_id = review_id;
    }

    int getReviewID() {
        return review_id;
    }
}
