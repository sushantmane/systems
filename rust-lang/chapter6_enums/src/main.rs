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