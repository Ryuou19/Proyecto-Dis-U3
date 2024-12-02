package com.icc.ratings_svc.configs;

import com.icc.ratings_svc.models.Rating;
import com.icc.ratings_svc.repositories.RatingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner seedDatabase(RatingRepository ratingRepository) {
        return args -> {
            if (ratingRepository.count() == 0) {
                Rating rating1 = new Rating();
                rating1.setStoreId(1L);
                rating1.setOrderId(1L);
                rating1.setRating(5);
                rating1.setComment("Excellent service and food!");
                rating1.setCreatedAt(LocalDateTime.now());
                ratingRepository.save(rating1);

                Rating rating2 = new Rating();
                rating2.setStoreId(1L);
                rating2.setOrderId(2L);
                rating2.setRating(4);
                rating2.setComment("Good experience, but delivery was a bit slow.");
                rating2.setCreatedAt(LocalDateTime.now());
                ratingRepository.save(rating2);

                Rating rating3 = new Rating();
                rating3.setStoreId(2L);
                rating3.setOrderId(3L);
                rating3.setRating(3);
                rating3.setComment("Average service. Food was okay.");
                rating3.setCreatedAt(LocalDateTime.now());
                ratingRepository.save(rating3);

                Rating rating4 = new Rating();
                rating4.setStoreId(3L);
                rating4.setOrderId(4L);
                rating4.setRating(2);
                rating4.setComment("Below average quality. Needs improvement.");
                rating4.setCreatedAt(LocalDateTime.now());
                ratingRepository.save(rating4);

                Rating rating5 = new Rating();
                rating5.setStoreId(4L);
                rating5.setOrderId(5L);
                rating5.setRating(1);
                rating5.setComment("Very poor service and food quality.");
                rating5.setCreatedAt(LocalDateTime.now());
                ratingRepository.save(rating5);

                System.out.println("Database seeded successfully.");
            } else {
                System.out.println("Database already contains data. Skipping seeding.");
            }
        };
    }
}
