package ca.concordia.soen487.lab5.server;

import jakarta.activation.DataHandler;
import jakarta.jws.WebService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Hashtable;

@WebService(name = "CustomerServiceImpl", endpointInterface = "ca.concordia.soen487.lab5.server.CustomerService")
public class CustomerServiceImpl implements CustomerService {

    Hashtable<Integer, Customer> customers;

    public CustomerServiceImpl() {
        this.customers = new Hashtable<>();
    }

    @Override
    public Customer getCustomer(int id) throws CustomerNotFound {
        if (!customers.containsKey(id)) throw new CustomerNotFound();
        return customers.get(id);
    }

    @Override
    public void addCustomer(Customer customer) throws IdAlreadyExists {
        if (customers.containsKey(customer.getId())) throw new IdAlreadyExists();
        customers.put(customer.getId(), customer);
    }

    @Override
    public void updateCustomer(int id, Customer customer) throws CustomerNotFound {
        if (!customers.containsKey(id)) throw new CustomerNotFound();
        customers.replace(id, customer);
    }

    @Override
    public void deleteCustomer(int id) throws CustomerNotFound {
        if (!customers.containsKey(id)) throw new CustomerNotFound();
        customers.remove(id);
    }

    @Override
    public String addPhoto(int id, byte[] file, String mime) {

        return String.valueOf(file.length);
    }

    @Override
    public String addPhotoV2(DataHandler file) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int total = 0;
        try (InputStream input = file.getInputStream()) {
            while (input.read(buffer.array()) > 0) {
                total += buffer.arrayOffset();
            }
        } catch (IOException e) {
            System.out.println("Oups still not working.");
        }
        return "Total " + total + " bytes received";
    }
}
