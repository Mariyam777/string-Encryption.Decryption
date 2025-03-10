import java.util.Scanner;

public class Main {

    // Method for Caesar Cipher encryption
    public static String encCaesar(String inputText, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        shift = (shift % 26 + 26) % 26;

        for (char ch : inputText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                encryptedText.append((char) ((ch - base + shift) % 26 + base));
            } else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

    // Method for Caesar Cipher decryption
    public static String decCaesar(String encryptedText, int shift) {
        return encCaesar(encryptedText, 26 - shift);  
    }

    // Method for Vigenère Cipher encryption
    public static String encVigenere(String inputText, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyInd = 0;

        for (char ch : inputText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                char keyChar = key.charAt(keyInd % key.length());
                int shift = Character.toLowerCase(keyChar) - 'a';
                encryptedText.append((char) ((ch - base + shift) % 26 + base));
                keyInd++;
            } else {
                encryptedText.append(ch);
            }
        }
        return encryptedText.toString();
    }

    // Method for Vigenère Cipher decryption
    public static String decVigenere(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyInd = 0;

        for (char ch : encryptedText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                char keyChar = key.charAt(keyInd % key.length());
                int shift = Character.toLowerCase(keyChar) - 'a';
                decryptedText.append((char) ((ch - base - shift + 26) % 26 + base));
                keyInd++;
            } else {
                decryptedText.append(ch);
            }
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int operationChoice = 0;

        while (operationChoice != 1 && operationChoice != 2) {
            System.out.println("CHOOSE OPERATION:");
            System.out.println(" 1 => ENCRYPTION ");
            System.out.println(" 2 => DECRYPTION ");
            System.out.print("ENTER 1 OR 2: ");
            if (scanner.hasNextInt()) {
                operationChoice = scanner.nextInt();
                scanner.nextLine();
                if (operationChoice != 1 && operationChoice != 2) {
                    System.out.println("INVALID OPERATION! PLEASE ENTER 1 OR 2");
                }
            } else {
                System.out.println("INVALID INPUT! PLEASE ENTER 1 OR 2");
                scanner.next();
            }
        }

        int methodChoice = 0;
        // Ensure valid input for method choice
        while (methodChoice != 1 && methodChoice != 2) {
            System.out.println("CHOOSE METHOD OF OPERATION:");
            System.out.println(" 1 => CAESAR CIPHER  ");
            System.out.println(" 2 => VIGENERE CIPHER ");
            System.out.print("ENTER 1 OR 2: ");
            if (scanner.hasNextInt()) {
                methodChoice = scanner.nextInt();
                scanner.nextLine();
                if (methodChoice != 1 && methodChoice != 2) {
                    System.out.println("INVALID OPERATION! PLEASE ENTER 1 OR 2.");
                }
            } else {
                System.out.println("INVALID INPUT ! PLEASE ENTER 1 OR 2.");
                scanner.next();
            }
        }

        if (methodChoice == 1) {
            // Caesar Cipher
            System.out.print("ENTER THE TEXT : ");
            String text = scanner.nextLine();
            int shift = -1;

            while (shift < 0 || shift > 25) {
                System.out.print("ENTER THE SHIFT VALUE (BETWEEN 0 AND 25) : ");
                if (scanner.hasNextInt()) {
                    shift = scanner.nextInt();
                    scanner.nextLine();
                    if (shift < 0 || shift > 25) {
                        System.out.println("INVALID SHIFT VALUE! PLEASE ENTER A VALUE BETWEEN 0 AND 25.");
                    }
                } else {
                    System.out.println("INVALID INPUT! PLEASE ENTER A VALID SHIFT VALUE (0-25).");
                    scanner.next();
                }
            }

            if (operationChoice == 1) {
                // Encrypt using Caesar Cipher
                System.out.println("ENCRYPTED TEXT => " + encCaesar(text, shift));
            } else {
                // Decrypt using Caesar Cipher
                System.out.println("DECRYPTED TEXT => " + decCaesar(text, shift));
            }
        } else {
            // Vigenère Cipher
            System.out.print("ENTER THE TEXT : ");
            String text = scanner.nextLine();
            String key = "";

            while (key.isEmpty()) {
                System.out.print("ENTER THE KEY: ");
                key = scanner.nextLine();
                if (key.isEmpty()) {
                    System.out.println("INVALID KEY! PLEASE ENTER A NON-EMPTY KEY.");
                }
            }

            if (operationChoice == 1) {
                // Encrypt using Vigenère Cipher
                System.out.println("ENCRYPTED TEXT => " + encVigenere(text, key));
            } else {
                // Decrypt using Vigenère Cipher
                System.out.println("DECRYPTED TEXT => " + decVigenere(text, key));
            }
        }

        scanner.close();
    }
}
