<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Form Application</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f6f8; }
        .container { max-width: 500px; margin: 40px auto; background: #fff;
                     padding: 30px; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,0.1); }
        h2 { text-align: center; color: #2c3e50; }
        label { display: block; margin-top: 15px; font-weight: bold; color: #333; }
        input, select { width: 100%; padding: 8px; margin-top: 5px; box-sizing: border-box;
                        border: 1px solid #ccc; border-radius: 4px; }
        button { margin-top: 25px; width: 100%; padding: 10px; background: #2c7be5;
                 color: #fff; border: none; border-radius: 4px; font-size: 16px; cursor: pointer; }
        button:hover { background: #1a5fc4; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Student Form Application</h2>
        <form action="submit" method="post">
            <label>First Name</label>
            <input type="text" name="firstName" required>

            <label>Last Name</label>
            <input type="text" name="lastName" required>

            <label>Date of Birth</label>
            <input type="date" name="dob" required>

            <label>Gender</label>
            <select name="gender" required>
                <option value="">-- Select --</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select>

            <label>Highest Qualification</label>
            <input type="text" name="highestqualification" required>

            <label>Year of Passing</label>
            <input type="number" name="year_of_passing" min="1950" max="2100" required>

            <label>Mobile Number</label>
            <input type="tel" name="mobilenumber" pattern="[0-9]{10}" title="10 digit mobile number" required>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
<!-- v1.1 -->
<-  — Angular dashboard ( Nginx + Ingress webhook test v1.2 -->
