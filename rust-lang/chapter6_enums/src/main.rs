// enum
fn main() {
    let four = IpAddrType::IPv4;
    dbg!(&four);
    let six = IpAddrType::IPv6;
    dbg!(&six);

    route(&four);
    route(&six);

    let localhost = IpAddr {
        kind: IpAddrType::IPv4,
        addr: String::from("127.0.0.1"),
    };

    let loopback = IpAddr {
        kind: IpAddrType::IPv6,
        addr: String::from("::1"),
    };

    display_ip_addr(&localhost);
    display_ip_addr(&loopback);


    // just enums
    let localhost = IpAddress::IPv4(String::from("127.0.0.1"));
    println!("ip: {:?}", localhost);

    let localhost = InterestingIpAddress::IPv4(127, 0, 0, 1);
    dbg!(localhost);

    let loopback = InterestingIpAddress::IPv6(String::from("::1"));
    dbg!(loopback);

    # [derive(Debug)]
    enum Message {
        Quit,
        Move { x: i32, y: i32}, // named fields; like struct
        Write(String),
        ChangeColor(i32, i32,i32),
    }

    // alternative to the above enum Message with structs would be
    struct QuitMessage; // unit struct
    struct MoveMessage {
        x: i32,
        y: i32,
    }
    struct WriteMessage(String); // tuple struct
    struct ChangeColorMessage(i32, i32, i32); // tuple struct


    // enums with message
    impl Message {
        fn display(&self) {
            println!("{:?}", self);
        }
    }

    let black = Message::ChangeColor(0, 0, 0);
    black.display();

    // custom
    let x: Optional<i32> = Optional::None;
    println!("optional x: {:?}", x);

    let x = Optional::Some(String::from("big bang theory"));
    println!("optional x: {:?}", x);


    // Option from lib
    let some_num = Some(5);
    let some_char = Some('c');
    let absent_number: Option<i32> = None;
    println!("option: {:?} - {} - {}",
             some_num, some_num.is_some(), some_num.expect("Failed to get the value"));
    println!("option: {:?} - {} - {}",
             some_char, some_char.is_some(), some_char.expect("Failed to get the value"));
    println!("option: {:?} - {}", absent_number, absent_number.is_some());
    // absent_number.expect("value is not present!!!"); <-- panic


}

// The Option Enum
#[derive(Debug)]
enum Optional<T> {
    None,
    Some(T),
}


#[derive(Debug)]
enum IpAddrType {
    IPv4,
    IPv6,
}

#[derive(Debug)]
struct IpAddr {
    kind: IpAddrType,
    addr: String,
}

fn route(ip_type: &IpAddrType) {
    println!("IP addr type: {:?}", ip_type);
}

fn display_ip_addr(ip_addr: &IpAddr) {
    println!("ip_addr: {:#?}", ip_addr);
}

#[derive(Debug)]
enum IpAddress {
    IPv4(String),
    IPv6(String),
}

#[derive(Debug)]
enum InterestingIpAddress {
    IPv4(u32, u32, u32, u32),
    IPv6(String),
}

enum IpAddrLib {
    IPv4(IpAddr),
    IPv6(IpAddr),
}