fn main() {
    // approach 1
    let width1: u32 = 30;
    let height1: u32 = 50;
    println!("AreaR of rectangle with width: {width1} and {height1} is {}",
             area(width1, height1));


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
}

fn area(width: u32, height: u32) -> u32 {
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

fn area_struct(rect: &Rectangle) -> u32 {
    rect.width * rect.height
}
