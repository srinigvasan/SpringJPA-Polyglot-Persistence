ALTER TABLE Reviews ADD FOREIGN KEY (productId) REFERENCES Product(productId);
ALTER TABLE Comments ADD FOREIGN KEY (reviewId) REFERENCES Reviews(reviewId);


