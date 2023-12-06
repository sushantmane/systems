pub trait Summary {
    fn summarize(&self) -> String;

    fn default_display(&self) -> String {
        self.summarize()
    }

    fn summarize_author(&self) -> String {
        String::from("anony")
    }
}

pub struct NewsArticle {
    pub headline: String,
    pub location: String,
    pub author: String,
    pub content: String,
}

impl Summary for NewsArticle {
    fn summarize(&self) -> String {
        format!("{}, by {} ({})", self.headline, self.author, self.location)
    }
}

pub struct Tweet {
    pub username: String,
    pub content: String,
    pub reply: bool,
    pub retweet: bool,
}

impl Summary for Tweet {
    fn summarize(&self) -> String {
        format!("{}: {}", self.username, self.content)
    }

    fn default_display(&self) -> String {
        String::from("overridden")
    }

    fn summarize_author(&self) -> String {
        format!("@{}", self.username)
    }
}

// Traits as Parameters
pub fn notify<T: Summary>(item: &T) {
    println!("Breaking news! {}", item.summarize());
}
