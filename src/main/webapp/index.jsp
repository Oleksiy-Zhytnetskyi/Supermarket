<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp"></script>
</head>
<body class="bg-blue-500">

<div class="bg-white mx-auto text-center w-1/2 shadow-xl rounded-3xl my-96 max-w-2x1">
    <h2 class="text-4xl font-semibold border-b pb-2 mx-6">Log in</h2>
    <h3 class="bg-red-300 text-red-900 font-semibold text-xl w-80 rounded-lg focus:outline-none focus:ring-2 mx-auto">${error}</h3>
    <form action="./login" method="post" role="form">
        <div class="grid grid-cols-2 gap-4 my-5 mx-8">
            <label for="email" class="text-xl flex items-center">Email:</label>
            <input class="w-full p-1 border-2 placeholder-blue-800 appearance-none rounded-lg focus:outline-none focus:ring-2" type="email" id="email" name="email">

            <label for="password" class="text-xl flex items-center">Password:</label>
            <input class="w-full p-1 border-2 placeholder-blue-800 appearance-none rounded-lg focus:outline-none focus:ring-2" type="password" id="password" name="password">
        </div>

        <button class="bg-blue-300 text-x1 font-semibold px-4 py-2 rounded-lg hover:bg-blue-800 hover:text-white" type="submit">Log In</button>
    </form>
</div>

</body>
</html>