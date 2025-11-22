# QA Portfolio Project – Manual & Automation Testing

This project showcases my **QA skills** through both **manual testing** and **automation testing** on the demo e-commerce site **SauceDemo**.

---

## What This Project Demonstrates

### Manual QA
- Test Plan & Scope  
- Test Scenarios  
- Test Cases with Expected/Actual Results  
- Bug Reports with Priority, Severity & Screenshots  
- Testing performed using *standard_user* and *problem_user*

### Automation QA
- Selenium WebDriver + Java  
- TestNG test suite  
- Page Object Model (POM)  
- Automatic screenshots on failure  
- Tests for login, product listing, product details, cart behavior & sorting  
- Automated reproduction of real bugs found with `problem_user`

---

## 📂 Project Structure

- Documentation/ → Test plan, scenarios, cases, bug reports
- src/main/java/pages/ → POM page classes
- src/test/java/base/ → BaseTest (driver, waits, screenshots)
- src/test/java/tests/ → Automated tests
- Screenshots/ → Images from failed tests
- testng.xml → Runs the full test suite


---

##  Running Automated Tests

1. Clone repo  
   `git clone https://github.com/yourusername/QA_Portfolio.git`
2. Open in IntelliJ  
3. Run `testng.xml`  
4. View screenshots in `Screenshots/`  
5. TestNG report (HTML): `test-output/index.html`  

---

##  Example Bugs Covered in Automation
- Product not removed from cart (`problem_user`)  
- Wrong product details shown  
- All products have same image  
- Sorting A→Z / Z→A not applied  

---

##  Purpose
This project is designed as a **complete QA showcase**, demonstrating:
- Test documentation  
- Real bug discovery  
- UI functional testing  
- Automation engineering  
- GitHub workflow  
- Professional reporting  

---

**Author:** Stevan Suvakov  
