import java.util.Scanner;

public class MyFunc {

    // Function to format full name
    public static String fullName(String firstName, String lastName) {
        // Convert first character to uppercase, rest to lowercase
        String formattedFirstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        String formattedLastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        return formattedFirstName + " " + formattedLastName;
    }

    // Function to validate and format phone number
    public static String phoneNumber(String phone) {
        if (phone.startsWith("9") && phone.length() == 10) {
            return "0" + phone;
        } else {
            return "Wrong entry. Try again.";
        }
    }

    // Function to validate and return user ID
    public static String userId(String id) {
        if (id.length() >= 4 && id.length() <= 13) {
            return id;
        } else {
            return "Wrong entry. Try again.";
        }
    }

    // Function to get interests and return as an array
    public static String[] getInterests(Scanner scanner) {
        String[] interests = new String[10]; // Assuming maximum of 10 interests
        System.out.println("Enter your interests (up to 10, enter 'done' to finish):");
        for (int i = 0; i < 10; i++) {
            String interest = scanner.nextLine().trim();
            if (interest.equalsIgnoreCase("done")) {
                break;
            }
            interests[i] = interest;
        }
        return interests;
    }

    // Function to display user's full information
    public static void userFullInformation(String fullName, String phoneNumber, String userID, String[] interests) {
        System.out.println("Hello! My name is " + fullName + ". My ID is " + userID + ". Here are some of my interests:");
        for (int i = 0; i < 10; i++) {
            if (interests[i] == null) {
                break;
            }
            System.out.println((i + 1) + ". " + interests[i]);
        }
        System.out.println("You can reach me via my phone number " + phoneNumber + ".");
    }

    // Function to encode information using Caesar cipher
    public static String informationEncoder(String information, int shift) {
        StringBuilder encodedText = new StringBuilder();
        for (char c : information.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encodedText.append((char) ((c - base + shift) % 26 + base));
            } else {
                encodedText.append(c);
            }
        }
        return encodedText.toString();
    }

    // Function to decode Caesar cipher encoded text
    public static String informationDecoder(String encodedText, int shift) {
        return informationEncoder(encodedText, 26 - (shift % 26)); // Decoding is essentially encoding with the inverse shift
    }

    // Main function to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Gathering user information
        System.out.println("Enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter your phone number:");
        String phone = scanner.nextLine();
        while (!phone.startsWith("9") || phone.length() != 10) {
            System.out.println("Wrong entry. Enter your phone number again:");
            phone = scanner.nextLine();
        }

        System.out.println("Enter your user ID:");
        String userID = scanner.nextLine();
        while (userID.length() < 4 || userID.length() > 13) {
            System.out.println("Wrong entry. Enter your user ID again:");
            userID = scanner.nextLine();
        }

        String fullName = fullName(firstName, lastName);
        String formattedPhone = phoneNumber(phone);
        String formattedID = userId(userID);
        String[] interests = getInterests(scanner);

        // Displaying user's full information
        userFullInformation(fullName, formattedPhone, formattedID, interests);

        // Encoding information example
        System.out.println("Would you like to encode your information? (yes/no)");
        String encodeChoice = scanner.nextLine().toLowerCase();
        if (encodeChoice.equals("yes")) {
            System.out.println("Enter information to encode:");
            String infoToEncode = scanner.nextLine();
            System.out.println("Enter shift for encoding (e.g., 3):");
            int shift = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            String encodedInfo = informationEncoder(infoToEncode, shift);
            System.out.println("Encoded information: " + encodedInfo);
            // Decoding example
            System.out.println("Would you like to decode the encoded information? (yes/no)");
            String decodeChoice = scanner.nextLine().toLowerCase();
            if (decodeChoice.equals("yes")) {
                String decodedInfo = informationDecoder(encodedInfo, shift);
                System.out.println("Decoded information: " + decodedInfo);
            }
        }

        scanner.close();
    }
}
