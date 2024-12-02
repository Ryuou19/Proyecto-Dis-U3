package com.icc.ratings_svc.domain;

import com.icc.ratings_svc.models.Rating;

import java.util.List;

public class RatingResponse {
    private double averageRating;
    private List<Rating> ratings;

    public RatingResponse(double averageRating, List<Rating> ratings) {
        this.averageRating = averageRating;
        this.ratings = ratings;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
