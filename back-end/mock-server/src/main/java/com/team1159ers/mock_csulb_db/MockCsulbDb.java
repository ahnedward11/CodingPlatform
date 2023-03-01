
package com.team1159ers.mock_csulb_db;

import com.team1159ers.mock_csulb_db.model.admin.Admin;
import com.team1159ers.mock_csulb_db.model.user.User;
import com.team1159ers.mock_csulb_db.service.admin.AdminService;
import com.team1159ers.mock_csulb_db.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SpringBootApplication
public class MockCsulbDb implements ApplicationRunner {
	// constant values
	private static final String firstNamesFilePath = "first-names.txt";
	private static final String lastNamesFilePath = "last-names.txt";
	private static final int passwordLength = 13;

	private final UserService userService;
	private final AdminService adminService;

	@Autowired
	public MockCsulbDb(UserService userService, AdminService adminService) {
		this.userService = userService;
		this.adminService = adminService;
	}

	@Override
	public void run(ApplicationArguments args) {
		/*List<Object> allAccounts = getAccountData();
		List<String> allUserEmails = userService.getAllEmails();
		List<String> allAdminEmails = adminService.getAllEmails();

		// Save the User/Admin objects if they don't exist in their table already (using emails to check)
		for (Object obj : allAccounts) {
			if (obj instanceof User) {
				User currentUser = (User) obj;
				if (!allUserEmails.contains(currentUser.getEmail()))
					userService.saveUser(currentUser);
			}
			else if (obj instanceof Admin) {
				Admin currentAdmin = (Admin) obj;
				if (!allAdminEmails.contains(currentAdmin.getEmail()))
					adminService.saveAdmin(currentAdmin);
			}
		}*/
	}

	/**
	 * Returns a list of randomly generated User and Admin objects.
	 */
	private static List<Object> getAccountData() {
		List<Object> allAccounts = new ArrayList<>();
		// Add default user/admin accounts for easy access
		User defaultUser = new User("user", "password", "default_user");
		User edwardUser = new User("edward.anh@student.csulb.edu", "password", "edward.anh");
		User stevenUser = new User("steven.dao01@student.csulb.edu", "password", "steven.dao01");
		User marieUser = new User("marie.payad@student.csulb.edu", "password", "marie.payad");
		User johnUser = new User("johnphilip.teano@student.csulb.edu", "password", "john.teano");

		Admin defaultAdmin = new Admin("admin", "password", "default_admin");
		Admin edwardAdmin = new Admin("edward.anh@csulb.edu", "password", "edward.anh");
		Admin stevenAdmin = new Admin("steven.dao01@csulb.edu", "password", "steven.dao01");
		Admin marieAdmin = new Admin("marie.payad@csulb.edu", "password", "marie.payad");
		Admin johnAdmin = new Admin("johnphilip.teano@csulb.edu", "password", "john.teano");

		allAccounts.add(defaultUser);
		allAccounts.add(edwardUser);
		allAccounts.add(stevenUser);
		allAccounts.add(marieUser);
		allAccounts.add(johnUser);

		allAccounts.add(defaultAdmin);
		allAccounts.add(edwardAdmin);
		allAccounts.add(stevenAdmin);
		allAccounts.add(marieAdmin);
		allAccounts.add(johnAdmin);

		// Includes several names like John, Edward, etc...
		List<String> firstNames = getNames(firstNamesFilePath);
		// Includes several names like Teano, Anh, etc...
		List<String> lastNames = getNames(lastNamesFilePath);

		// Stores all previously generated names as keys, and the number of repeats as values
		Map<String, Integer> recordedUserFullNames = new HashMap<>();
		Map<String, Integer> recordedAdminFullNames = new HashMap<>();

		// 100,000 total Users/Admins
		for (int i = 0; i < 100_000; ++i) {
			// Random decimal between 0.0000 and 1.0000
			double randomProbability = Math.random();

			// 80% chance to be a User
			if (randomProbability <= 0.8) {
				// Store a User object with fields:
				User newUser = new User();
				// Randomly generated unique name in format "firstName.lastName(suffix)"
				String uniqueName = getUniqueName(firstNames, lastNames, recordedUserFullNames);
				// Profile name as unique name
				newUser.setProfileName(uniqueName);
				// Email as unique email prefix + User suffix
				newUser.setEmail(uniqueName + "@student.csulb.edu");
				// Other fields from RS...
				newUser.setPassword(getRandomPassword());

				// Add User to the list of Objects to return
				allAccounts.add(newUser);

				// Else, 20% chance to be an Admin
			} else {
				// Store an Admin object with fields:
				Admin newAdmin = new Admin();
				// Randomly generated unique name in format "firstName.lastName(suffix)"
				String uniqueName = getUniqueName(firstNames, lastNames, recordedAdminFullNames);
				// Profile name as unique name
				newAdmin.setProfileName(uniqueName);
				// Email as unique email prefix + Admin suffix
				newAdmin.setEmail(uniqueName + "@csulb.edu");
				// Other fields from RS...
				newAdmin.setPassword(getRandomPassword());

				// Add Admin to the list of Objects to return
				allAccounts.add(newAdmin);
			}
		}

		return allAccounts;
	}

	/**
	 * Returns the list of names from the file path (one name per line).
	 * @return the full list of names
	 */
	private static List<String> getNames(String filePath) {
		List<String> firstNames = new ArrayList<>();
		String nextLine;

		try (BufferedReader bReader = new BufferedReader(new FileReader(filePath))) {
			// proceed until the reader reaches the last line
			while ((nextLine = bReader.readLine()) != null) {
				firstNames.add(nextLine);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return firstNames;
	}

	/**
	 * Returns a unique email name prefix relative to the provided list of previously recorded User/Admin names.
	 * @param firstNames the list of first names to randomly choose from
	 * @param lastNames the list of last names to randomly choose from
	 * @param recordedFullNames the list of previously recorded names
	 * @return a unique email relative to the provided list of previously recorded names
	 */
	private static String getUniqueName(List<String> firstNames, List<String> lastNames,
										 Map<String, Integer> recordedFullNames) {
		int maxFirstNamesIndex = firstNames.size() - 1;
		int maxLastNamesIndex = lastNames.size() - 1;

		String randomFirstName = firstNames.get((int) (Math.random() * maxFirstNamesIndex)).toLowerCase();
		String randomLastName = lastNames.get((int) (Math.random() * maxLastNamesIndex)).toLowerCase();
		String fullName = randomFirstName + "." + randomLastName;

		// If this full name hasn't been created previously, record and return it without an integer suffix
		if (recordedFullNames.get(fullName) == null) {
			recordedFullNames.put(fullName, 0);
			return fullName;

			// Else, record and return the name with the proper suffix
		} else {
			int suffix = recordedFullNames.get(fullName) + 1;
			recordedFullNames.replace(fullName, suffix);
			return (fullName + suffix);
		}
	}

	/**
	 * Returns a randomly generated ASCII-character password of the set length.
	 * @return a randomly generated ASCII-character password
	 */
	private static String getRandomPassword() {
		StringBuilder password = new StringBuilder();

		for (int i = 0; i < passwordLength; ++i) {
			// Generate a random ASCII character code between 33(!) and 126(~)
			int randomAsciiCode = (int) (Math.random() * 93) + 33;
			password.append((char) randomAsciiCode);
		}

		return password.toString();
	}
}
