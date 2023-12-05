// Trait

use crate::lib::{NewsArticle, Summary, Tweet};

mod lib;
mod news;

fn main() {
    let tweet = Tweet {
        username: String::from("horse_ebooks"),
        content: String::from("of course, as you probably already know, people"),
        reply: false,
        retweet: false,
    };

    println!("1 new tweet: {}", tweet.summarize());
    println!("1 new tweet: {}", tweet.default_display());

    let article = NewsArticle {
        headline: String::from("Penguins win the Stanley Cup Championship!"),
        location: String::from("South Pole"),
        author: String::from("Iceberg"),
        content: String::from("Best match ever!"),
    };

    println!("New article: {}", article.summarize());
    println!("Display article: {}", article.default_display());
}
