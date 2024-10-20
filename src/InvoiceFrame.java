import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class InvoiceFrame extends JFrame {
    JPanel mainPnl;
    JPanel customerPnl;
    JPanel ItemPnl;
    JPanel OrderPnl;
    JLabel title;
    JLabel cusName;
    JLabel cusAddress;
    JLabel ItemName;
    JLabel ItemPrice;
    JTextArea cusNameBox;
    JTextArea cusAddressBox;
    JTextArea ItemBox;
    JTextArea PriceBox;
    JButton addItemBtn;
    JButton orderBtn;
    ArrayList<ItemMaker> itemFields;  // Store ItemMaker objects
    private String res;

    JOptionPane orderYN;

    public InvoiceFrame(){
        itemFields = new ArrayList<>();
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayout(5,1));


        //Title probably doesn't need its own method
        title = new JLabel("Invoice", JLabel.CENTER);
        title.setFont(new Font("Impact", Font.PLAIN, 36));
        mainPnl.add(title, BorderLayout.NORTH);

        Customer();
        mainPnl.add(customerPnl);

        Item();
        mainPnl.add(ItemPnl);

        Order();
        mainPnl.add(OrderPnl);

        add(mainPnl);


        setSize(600,750);
        setLocation(0,0);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void Customer(){
        customerPnl = new JPanel();
        cusName = new JLabel();
        cusAddress = new JLabel();

        cusName.setText("Customer Name:");
        cusNameBox = new JTextArea(1, 15);
        //make a text box so customer can enter name

        cusAddress.setText("Customer Address");
        cusAddressBox = new JTextArea(1,15);
        //same thing but with adress

        customerPnl.add(cusName);
        customerPnl.add(cusNameBox);
        customerPnl.add(cusAddress);
        customerPnl.add(cusAddressBox );
    }

    private void Item(){
        ItemPnl = new JPanel();
        ItemName = new JLabel();
        ItemPrice = new JLabel();
        ItemBox = new JTextArea(1, 15);
        PriceBox = new JTextArea(1,15);

        ItemName.setText("Enter Item Name:");
        ItemPrice.setText("Enter Item Cost:");

        ItemPnl.add(ItemName);
        ItemPnl.add(ItemBox);
        ItemPnl.add(ItemPrice);
        ItemPnl.add(PriceBox);

        addItemBtn = new JButton("Add Item");
        addItemBtn.addActionListener((ActionEvent ae) -> {
            String name = ItemBox.getText();
            double unitPrice = Double.parseDouble(PriceBox.getText()); //think this turns the text into a double
            unitPrice = Math.round(unitPrice * 100.0) / 100.0;
            ItemMaker itemMaker = new ItemMaker(name, unitPrice);
            itemFields.add(itemMaker);
            ItemBox.setText("");
            PriceBox.setText("");
        });
        ItemPnl.add(addItemBtn);
    }

    private void Order(){
        OrderPnl = new JPanel();
        orderBtn = new JButton("Order");
        orderYN = new JOptionPane();
        orderBtn.addActionListener((ActionEvent ae) -> {
            res = "";
            res += "Customer Name: " + cusNameBox.getText() + "\nCustomer Address: " + cusAddressBox.getText();
            for(ItemMaker item : itemFields){
                res += "\nItem: " + item.getName() + ", Price: " + item.getUnitPrice() + "\n";
            }
            int response = JOptionPane.showConfirmDialog(this, res, "Order?", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        OrderPnl.add(orderBtn);
    }


}
