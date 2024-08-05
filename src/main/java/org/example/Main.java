package org.example;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import org.example.domain.OrderItem;
import org.example.domain.User;
import org.example.jdbcTest.Product;
import org.example.repository.OrderItemsRepository;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.example.repositoryImpl.OrderItemsRepositoryImpl;
import org.example.repositoryImpl.OrderRepositoryImpl;
import org.example.repositoryImpl.ProductRepositoryImpl;
import org.example.repositoryImpl.UserRepositoryImpl;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) {

        while (true) {
            System.out.println("도메인 메뉴를 선택하세요");
            System.out.println("1. 상품");
            System.out.println("2. 주문");
            System.out.println("3. 회원");
            System.out.println("3. 종료");

            try {
                int menu = Integer.parseInt(br.readLine());
                switch (menu) {
                    case 1:
                        System.out.println("상품 메뉴입니다.");
                        System.out.println("1. 상품 등록");
                        System.out.println("2. 상품 조회");
                        System.out.println("3. 상품 수정");
                        System.out.println("4. 상품 삭제");
                        int productMenu = Integer.parseInt(br.readLine());
                        executeProductMenu(productMenu);
                        break;
                    case 2:
                        System.out.println("주문 메뉴입니다.");
                        System.out.println("1. 주문 등록");
                        System.out.println("2. 주문 조회");
                        System.out.println("3. 주문 수정");
                        System.out.println("4. 주문 삭제");
                        int orderMenu = Integer.parseInt(br.readLine());
                        executeOrderMenu(orderMenu);
                        break;
                    case 3:
                        System.out.println("회원 메뉴입니다.");
                        System.out.println("1. 회원 등록");
                        System.out.println("2. 회원 조회");
                        System.out.println("3. 회원 수정");
                        System.out.println("4. 회원 삭제");
                        int userMenu = Integer.parseInt(br.readLine());
                        executeUserMenu(userMenu);
                        break;
                    case 4:
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    default:
                        System.out.println("잘못된 메뉴입니다.");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void executeUserMenu(int userMenu) {

        UserRepositoryImpl userRepository = new UserRepositoryImpl();

        try {
            switch (userMenu) {
                case 1:
                    System.out.println("회원 이름, 이메일, 비밀번호, 전화번호를 입력해주세요.");
                    String name = br.readLine();
                    String email = br.readLine();
                    String password = br.readLine();
                    String phoneNumber = br.readLine();
                    userRepository.save(new User(name, email, password, phoneNumber));
                    break;
                case 2:
                    System.out.println("회원 조회 메뉴입니다.");
                    userRepository.findAll()
                        .forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("수정할 회원의 이름, 이메일, 전화번호를 입력해주세요.");
                    String userName = br.readLine();
                    String userEmail = br.readLine();
                    String userPassword = br.readLine();
                    String userPhoneNumber = br.readLine();
                    List<User> users = userRepository.findByEmail(userEmail);
                    User user = users.get(0);
                    userRepository.update(new User(user.getUserId(), userName, userEmail, userPassword, userPhoneNumber, user.getCreatedAt()));
                    break;
                case 4:
                    System.out.println("회원 삭제 메뉴입니다.");
                    System.out.println("삭제할 회원의 ID을 입력해주세요.");
                    long userId = Long.parseLong(br.readLine());
                    userRepository.deleteById(userId);
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다.");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void executeOrderMenu(int orderMenu) {
        OrderRepository orderService = new OrderRepositoryImpl();
        OrderItemsRepository orderItemsRepository = new OrderItemsRepositoryImpl();
        try {
            switch (orderMenu) {
                case 1:
                    System.out.println("주문자 ID, 주문 요청사항, 주문 상품 ID 리스트를 입력해주세요.");
                    int userId = Integer.parseInt(br.readLine());
                    String orderDetail = br.readLine();
                    String[] orderProductIds = br.readLine().split(",");
                    int savedId = orderService.save(userId, orderDetail, "ORDERED");

                    for (String orderProductId : orderProductIds) {
                        OrderItem orderItem = new OrderItem(Long.parseLong(String.valueOf(savedId)),
                            Long.parseLong(orderProductId));
                        orderItemsRepository.save(orderItem);
                    }
                    break;
                case 2:
                    System.out.println("주문 조회 메뉴입니다. 주문 ID를 입력해주세요");
                    int orderId = Integer.parseInt(br.readLine());
                    orderService.findByOrderId(orderId);
                    break;
                case 3:
                    System.out.println("수정할 주문의 ID와 주문 상품 ID, 주문 수량, 주문자 ID를 입력해주세요.");
                    break;
                case 4:
                    System.out.println("삭제할 주문의 ID를 입력해주세요.");
                    int deleteOrderId = Integer.parseInt(br.readLine());
                    orderService.delete(deleteOrderId);
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다.");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void executeProductMenu(int productMenu) {

        ProductRepository productRepository = new ProductRepositoryImpl();
        try {
            switch (productMenu) {
                case 1:
                    System.out.println("상품 이름, 가격, 수량을 입력해주세요.");
                    String name = br.readLine();
                    BigDecimal price = BigDecimal.valueOf(Integer.parseInt(br.readLine()));
                    int quantity = Integer.parseInt(br.readLine());
                    productRepository.save(Product.of(name, price, quantity));
                    break;
                case 2:
                    System.out.println("상품 조회 메뉴입니다.");
                    productRepository.findAll()
                        .forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("수정할 상품 ID와 상품 이름, 가격, 수량을 입력해주세요.");
                    Long productId = Long.parseLong(br.readLine());
                    String productName = br.readLine();
                    BigDecimal productPrice = BigDecimal.valueOf(Integer.parseInt(br.readLine()));
                    int productQuantity = Integer.parseInt(br.readLine());
                    Product product = productRepository.findById(productId);
                    productRepository.update(Product.of(productId, productName, productPrice, productQuantity, product.createdAt()));
                    break;
                case 4:
                    System.out.println("삭제할 상품의 ID를 입력해주세요.");
                    Long deleteProductId = Long.parseLong(br.readLine());
                    productRepository.deleteById(deleteProductId);
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다.");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}