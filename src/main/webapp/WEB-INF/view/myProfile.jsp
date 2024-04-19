<%--
  Created by IntelliJ IDEA.
  User: k1ng
  Date: 19.04.2024
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/view/header.jsp"%>
<div class="flex justify-center h-full mt-16">
    <form class="w-4/5">
        <div class="space-y-12">
            <div class=" pb-12">
                <h2 class="text-3xl font-semibold leading-7 text-gray-900">Profile</h2>

                <div class=" pb-12">
                    <h2 class="text-2xl font-semibold leading-7 text-gray-900">Personal Information</h2>

                    <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                        <div class="sm:col-span-2">
                            <label for="first-name" class="block text-xl font-medium leading-6 text-gray-900">First name</label>
                            <div class="mt-2">
                                <input type="text" name="first-name" id="first-name" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" value="${user.getName()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="last-name" class="block text-xl font-medium leading-6 text-gray-900">Surname</label>
                            <div class="mt-2">
                                <input type="text" name="last-name" id="last-name" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" value="${user.getSurname()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="patronymic" class="block text-xl font-medium leading-6 text-gray-900">Patronymic</label>
                            <div class="mt-2">
                                <input id="patronymic" name="patronymic" type="text" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" value="${user.getPatronymic()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-4">
                            <label for="email" class="block text-xl font-medium leading-6 text-gray-900">Email address</label>
                            <div class="mt-2">
                                <input type="text" name="email" id="email" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" value="${user.getEmail()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="phone" class="block text-xl font-medium leading-6 text-gray-900">Phone</label>
                            <div class="mt-2">
                                <input type="text" name="last-name" id="phone" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" value="${user.getPhone()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="role" class="block text-xl font-medium leading-6 text-gray-900">User role</label>
                            <div class="mt-2">
                                <input type="text" name="role" id="role" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" value="${user.getRole()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="date-of-birth" class="block text-xl font-medium leading-6 text-gray-900">Date of birth</label>
                            <div class="mt-2">
                                <input type="text" name="date-of-birth" id="date-of-birth" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" value="${user.getDateOfBirth()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="city" class="block text-xl font-medium leading-6 text-gray-900">City</label>
                            <div class="mt-2">
                                <input type="text" name="city" id="city" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" value="${user.getCity()}" readonly>
                            </div>
                        </div>

                        <div class="col-span-4">
                            <label for="street-address" class="block text-xl font-medium leading-6 text-gray-900">Street address</label>
                            <div class="mt-2">
                                <input type="text" name="street-address" id="street-address" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" value="${user.getStreet()}" readonly>
                            </div>
                        </div>

                        <div class="sm:col-span-2">
                            <label for="postal-code" class="block text-xl font-medium leading-6 text-gray-900">ZIP / Postal code</label>
                            <div class="mt-2">
                                <input type="text" name="postal-code" id="postal-code" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-lg sm:leading-6" value="${user.getZipCode()}" readonly>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
