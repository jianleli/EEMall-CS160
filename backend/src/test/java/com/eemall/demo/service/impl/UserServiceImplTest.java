package com.eemall.demo.service.impl;



import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.eemall.demo.model.Order;
import com.eemall.demo.model.User;
import com.eemall.demo.model.shoppingCart.Cart;
import com.eemall.demo.repository.CartRepository;
import com.eemall.demo.repository.OrderRepository;
import com.eemall.demo.repository.UserRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserServiceImplTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testFindByEmail() {
        String email = "i@gmail.com";
        User userInDB = new User();
        userInDB.setName("Jack");
        when(userRepository.findByEmail(email)).thenReturn(userInDB);

        User user = userService.findByEmail(email);
        assert(user == userInDB);
    }

    @Test
    public void testFindAllByRole() {
        String roll = "CUSTOMER";
        Collection<User> usersInDB = new ArrayList<>();
        when(userRepository.findAllByRole(roll)).thenReturn(usersInDB);

        Collection<User> list = userService.findAllByRole(roll);
        assert(list == usersInDB);
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setName("Jack");
        user.setPassword("123456");

        List<User> usersInDB = new ArrayList<>();
        when(passwordEncoder.encode(any(String.class))).thenReturn("AAAAAAAAAA");
        Cart cart = new Cart();
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        when(userRepository.save(user)).then(new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                User u = invocation.getArgument(0);
                usersInDB.add(u);
                return usersInDB.get(0);
            }

        });

        User u = userService.save(user);
        assert(u == usersInDB.get(0));
        assert(u.getCart() == cart);
    }

    @Test
    public void testUpdate() {
        String email = "i@gmail.com";
        User userInDB = new User();
        userInDB.setName("old Name");
        userInDB.setEmail(email);

        User newValue = new User();
        newValue.setName("Jack");
        newValue.setEmail(email);

        List<User> usersInDB = new ArrayList<>();
        usersInDB.add(userInDB);

        when(userRepository.findByEmail(email)).thenReturn(usersInDB.get(0));
        Answer answer = new Answer() {

            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                User u = invocation.getArgument(0);
                usersInDB.set(0, u);
                return u;
            }

        };
        when(userRepository.save(any(User.class))).thenAnswer(answer);

        User user = userService.update(newValue);

        assert(user == userInDB);
        assert(user.getName() == "Jack");
        assert(userInDB == usersInDB.get(0));
        assert(usersInDB.size() == 1);

    }

    @Test
    public void testGetOrdersString() {
        String email = "i@gmail.com";
        List<Order> ordersInDB = new ArrayList<>();
        when(orderRepository.findByCustomerEmail(email)).thenReturn(ordersInDB);

        List<Order> orders = userService.getOrders(email);
        assert(orders == ordersInDB);

    }

    @Test
    public void testGetOrdersStringPageable() {
       /* class MyPage<T> implements Page<T> {

            @Override
            public int getNumber() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public int getSize() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public List<T> getContent() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean hasContent() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public Sort getSort() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean isFirst() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean isLast() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean hasPrevious() {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public Pageable nextPageable() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Pageable previousPageable() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Iterator<T> iterator() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public int getTotalPages() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public long getTotalElements() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public <U> Page<U> map(Function<? super T, ? extends U> converter) {
                // TODO Auto-generated method stub
                return null;
            }

        }

        class MyPageable implements Pageable {

            @Override
            public int getPageNumber() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public int getPageSize() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public long getOffset() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public Sort getSort() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Pageable next() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public Pageable first() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public boolean hasPrevious() {
                // TODO Auto-generated method stub
                return false;
            }

        }*/
        Pageable pag = mock(Pageable.class);
        Page<Order> expected = mock(Page.class);

        String email = "i@gmail.com";
        //Page<Order> ordersInDB = new MyPage<Order>();
        //Pageable pageable = new MyPageable();
        //when(orderRepository.findByCustomerEmailOrderByDateCreatedDesc(email, pageable)).thenReturn(ordersInDB);
        when(orderRepository.findByCustomerEmailOrderByDateCreatedDesc(email, pag)).thenReturn(expected);

        //Page<Order> orders = userService.getOrders(email, pageable);
        //assert(orders == ordersInDB);
        Page<Order> orders = userService.getOrders(email, pag);
        assert(orders == expected);
    }

}
