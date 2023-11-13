fn main() {
    // approach 1
    let width1: u32 = 30;
    let height1: u32 = 50;
    println!("AreaR of rectangle with width: {width1} and {height1} is {}",
             area_regular(width1, height1));


    // approach 2
    let rect1: (u32, u32) = (width1, height1);
    println!("AreaT of rectangle with width: {} and {} is {}",
             rect1.0, rect1.1, area_t(rect1));


    // approach 3
    let rect = Rectangle {
        width: dbg!(width1),
        height: height1
    };
    println!("rect struct: {:?} - {:#?}", rect, rect);
    dbg!(&rect);

    println!("AreaS of rectangle with width: {} and {} is {}",
             rect.width, rect.height, area_struct(&rect));

    // approach 4
    println!("AreaM of rectangle with width: {} and {} is {}",
             rect.width, rect.height, rect.area());


    // other examples
    let rect2 = Rectangle {
        width: 10,
        height: 40
    };

    let rect3 = Rectangle {
        width: 60,
        height: 45
    };

    println!("rect1: {:?} can hold: {:?} => {}", rect, rect2, rect.can_hold(&rect2));
    println!("rect1: {:?} can hold: {:?} => {}", rect, rect3, rect.can_hold(&rect3));

    let square1 = Rectangle::square(4);
    println!("Area square: {:?} is {}", square1, square1.area());
}

fn area_regular(width: u32, height: u32) -> u32 {
    width * height
}

fn area_t(rect: (u32, u32)) -> u32 {
    rect.0 * rect.1
}


#[derive(Debug)]
struct Rectangle {
    width: u32,
    height: u32
}

impl Rectangle {
    fn area(&self) -> u32 {
        self.width * self.height
    }

    fn width(&self) -> u32 {
        self.width
    }

    fn height(&self) -> u32 {
        self.height
    }

    fn can_hold(&self, other: &Rectangle) -> bool {
        self.width > other.width && self.height > other.height
    }

    // Associated functions
    fn square(size: u32) -> Self {
        Self {
            width: size,
            height: size
        }
    }
}

fn area_struct(rect: &Rectangle) -> u32 {
    rect.width * rect.height
}
