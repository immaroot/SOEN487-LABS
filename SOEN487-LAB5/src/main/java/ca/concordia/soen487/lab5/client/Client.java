package ca.concordia.soen487.lab5.client;

import ca.concordia.soen487.lab5.client.generated.*;


public class Client {

    public static void main(String[] args) {

        Address address1 = new Address();
        address1.setCity("Jimmieland");
        address1.setZip("63104-5306");
        address1.setBuildingAndStreet("470 Douglas Centers");

        Address address2 = new Address();
        address2.setCity("Simonborough");
        address2.setZip("79548");
        address2.setBuildingAndStreet("42879 Fritsch Ports");

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("Torri Wilkinson MD");
        customer1.setAddress(address1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Lupe Murray");
        customer2.setAddress(address2);

        CustomerServiceImplService service = new CustomerServiceImplService();
        CustomerService port = service.getCustomerServiceImplPort();

//        try {
//            port.addCustomer(customer1);
//            port.addCustomer(customer2);
//        } catch (IdAlreadyExists_Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }


        try {
            System.out.println(port.getCustomer(1));
            System.out.println(port.getCustomer(2));
        } catch (CustomerNotFound_Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
