package com.pluralsight.testing.m2;

//had to go into file->project structure and change "java" to be a "testing" directory by "marking as test"!!
//then it let me add classes to it
//then had to manually type the package name at the top
//then had to click on the red light bulb to "move the package"
//then it was right

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.pluralsight.testing.m2.CoffeeType.Espresso;
import static com.pluralsight.testing.m2.CoffeeType.Latte;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class CafeTest {

        public static final int ESPRESSO_BEANS = Espresso.getRequiredBeans();
        public static final int NO_MILK = 0;
        public static final int NO_BEANS = 0;

//    public CafeTest(){
//        System.out.println("Constructor");
//    }


//    @BeforeClass
//    public static void beforeClass(){
//        System.out.println("Before Class");
//    }
//
//    @AfterClass
//    public static void afterClass(){
//        System.out.println("After Class");
//    }

        private Cafe cafe;

        @Before
        public void before(){
            System.out.println("Before");
            cafe = new Cafe();
        }

        @After
        public void after(){
            System.out.println("After");
        }


        /* can brew espresso */
        @Test
        public void canBrewEspresso(){

            //Given...
            withBeans();

            //When...
            Coffee coffee = cafe.brew(Espresso);

            //Then...

            //adding in the hamcrest tests -- has a property called "getBeans"
            assertThat(coffee, hasProperty("beans", equalTo(ESPRESSO_BEANS)));

            // make sure it's an espresso
            Assert.assertEquals("Wrong type of coffee", Espresso, coffee.getType());

            // make sure it's got no milk
            Assert.assertEquals("Wrong amount of milk",NO_MILK,coffee.getMilk());

            // deliberately make an error
            //Assert.assertEquals("Wrong quantity of coffee", 1,coffee.getMilk());

            // make sure there's enough coffee in it
            //Assert.assertEquals("Wrong number of beans",ESPRESSO_BEANS,coffee.getBeans());
            //assertThat("1" is()
        }


        @Test
        public void canBrewLatte(){

            //Given...
            withBeans();
            cafe.restockMilk(Latte.getRequiredMilk());

            //When...
            Coffee coffee = cafe.brew(Latte);

            //Then...
            // make sure it's an espresso
            //Assert.assertEquals(CoffeeType.Latte, coffee.getType());
            Assert.assertEquals("Wrong coffee type", Latte, coffee.getType());

            // make sure it's got no milk
            Assert.assertEquals(227,coffee.getMilk());

            // make sure there's enough coffee in it
            Assert.assertEquals(7,coffee.getBeans());
        }




        @Test
        public void brewingEspressoConsumesBeans(){

            //Given...
            withBeans();

            //When...
            Coffee coffee = cafe.brew(Espresso);

            //Then...
            //Restocked with 7 beans, and then used them all in the espresso, so beans in stock should be
            //decremented to zero
            Assert.assertEquals(NO_BEANS, cafe.getBeansInStock());
            // Assert.assertThat()

        }

        @Test(expected = IllegalArgumentException.class)
        public void mustRestockMilk(){

            //When...
            cafe.restockMilk(NO_MILK);

        }

        @Test(expected = IllegalArgumentException.class)
        public void mustRestockBeans(){
            //When...
            cafe.restockBeans(NO_BEANS);

        }


        //test the idea of brewing a latte and it requires milk
        // need to have a then clause that says exceptions are okay
        @Test(expected = IllegalStateException.class)
        //@Test
        public void lattesRequiresMilk(){

            //Given...
            withBeans();

            //When...
            Coffee coffee = cafe.brew(Latte);


        }

        private void withBeans() {
            cafe.restockBeans(ESPRESSO_BEANS);
        }




    }
