<%@ page import="com.gathergrid.gathergridfeatures.domain.enums.TicketType" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="modalEditEvent" tabindex="-1" aria-hidden="true"
     class="fixed top-0 left-0 right-0 z-50 hidden w-full p-4 overflow-x-hidden overflow-y-auto md:inset-0 h-[calc(100%-1rem)] max-h-full">
    <div class="relative w-full max-w-2xl max-h-full">
        <!-- Modal content -->
        <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
            <!-- Modal header -->
            <div class="flex items-start justify-between p-4 border-b rounded-t dark:border-gray-600">
                <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
                    Edit event
                </h3>
                <button type="button"
                        class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ml-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                        data-modal-hide="modalEditEvent">
                    <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                         viewBox="0 0 14 14">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                              d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6" />
                    </svg>
                    <span class="sr-only">Close modal</span>
                </button>
            </div>
            <!-- Modal body -->
            <form action="${pageContext.request.contextPath}/event/update" method="post" id="containerForm-update">
                <div class="p-6 space-y-4 ">
                    <div>
                        <%--@declare id="name-event"--%><label for="name-event"
                                                               class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Name Event</label>
                        <input type="text" name="name-event"
                               class="name-event-edit bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required>
                    </div>
                    <input type="hidden" name="idEvent" class="idEvent" />
                    <label for="category-edit" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Select
                        an category</label>
                    <select id="category-edit" name="category-edit"
                            class="category-edit bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500">
                        <c:forEach items="${listCategory}" var="category">
                            <option value="<c:out value="${category.id}" />"><c:out value="${category.name}" /></option>
                        </c:forEach>
                    </select>
                    <label for="descriptionEventUpdate" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Date
                        event</label>
                    <input type="datetime-local" id="dateEventUpdated" name="dateEventUpdated"
                           class="dateEventUpdated bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                           placeholder="date event" required>
                    <label for="descriptionEventUpdate"
                           class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Description of
                        event</label>
                    <textarea id="descriptionEventUpdate" rows="4" name="descriptionEventUpdate"
                              class="descriptionEventUpdate block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                              placeholder="Write your description here..."></textarea>
                </div>
                <div class="p-6 espace-y-4 b-0 border-b-2 py-2 flex justify-between items-center">
                    <h2 class="text-xl font-semibold text-gray-900 dark:text-white">
                        Create un ticket</h2>
                </div>
                <div id="show_item-updated">
                    <!--- child here -->
                </div>
                <input type="hidden" hidden name="action" value="update">

                <!-- Modal footer -->
                <div class="flex items-center p-6 space-x-2 border-t border-gray-200 rounded-b dark:border-gray-600">
                    <button type="submit"
                            class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Edit</button>
                    <button data-modal-hide="modalEditEvent" type="button"
                            class="text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600">Decline</button>
                </div>
            </form>

        </div>
    </div>
</div>
