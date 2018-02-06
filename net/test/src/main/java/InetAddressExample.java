import java.net.*;
import java.util.Enumeration;

/**
 * Created by Tian on 2017/7/11.
 */
public class InetAddressExample {
    public static void main(String[] args) {

        // Get the network interfaces and associated addresses for this host
        try {
            final Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();

            if (interfaceList == null) {
                System.out.println("-- No interfaces found --");
            } else {
                while (interfaceList.hasMoreElements()) {
                    final NetworkInterface netif = interfaceList.nextElement();
                    System.out.println("Interface " + netif.getName() + ":");

                    final Enumeration<InetAddress> addrList = netif.getInetAddresses();
                    if (!addrList.hasMoreElements()) {
                        System.out.println("\t(No address for this interface)");
                    }
                    while (addrList.hasMoreElements()) {
                        final InetAddress address = addrList.nextElement();
                        System.out.print("\tAddress "
                                + ((address instanceof Inet4Address ? "(v4)"
                                : (address instanceof Inet6Address ? "(v6)" : "(?)"))));
                        System.out.println(": " + address.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Error getting network interfaces:" + e.getMessage());
        }

        // Get name(s)/address(es) of hosts given on command line
        for (String host : args) {
            try {
                System.out.println(host + ":");
                final InetAddress[] addressList = InetAddress.getAllByName(host);
                for (InetAddress address : addressList) {
                    System.out.println("\t" + address.getHostName() + "/" + address.getHostAddress());
                }
            } catch (UnknownHostException e) {
                System.out.println("\tUnable to find address for " + host);
            }
        }
    }
}
