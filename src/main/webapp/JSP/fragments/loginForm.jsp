<form id="loginForm" action="/login" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>

    <label for="user_type"> choose user pls:</label>
    <select id="user_type" name="user_type">
    <option value="students">student</option>
    <option value="Teachers">Teacher</option>
    </select>
    <input type="submit" id="loginSubmit" name="loginSubmit">
</form>