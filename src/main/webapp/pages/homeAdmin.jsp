<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="i-name">Dashboard</h3>
    <div class="values">
        <div class="val-box">
            <i class="fas fa-users"></i>
            <div>
                <h3>8</h3>
                <span>Nouveaux candidats</span>
            </div>
        </div>
        <div class="val-box">
            <i class="fa-solid fa-suitcase"></i>
            <div>
                <h3>8</h3>
                <span>Posts total</span>
            </div>
        </div>
        <div class="val-box">
            <i class="fa-sharp fa-solid fa-briefcase-blank"></i>
            <div>
                <h3>8</h3>
                <span>Nouveaux posts</span>
            </div>
        </div>
        <div class="val-box">
            <i class="fa-solid fa-traffic-cone"></i>
            <div>
                <h3>8</h3>
                <span>Nombre de posts signalés</span>
            </div>
        </div>
    </div>
    <div class="board">
        <table width="100%">
            <thead>
            <tr>
                <td>nom d'events</td>
                <td>description d'events</td>
                <td>nombre de tickets</td>
                <td>date d'event</td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listEvent}" var="event">
                <tr>
                    <td class="nomEntreprise">
                        <img src="avatarEntre.png" alt="" />
                        <a href="#">
                            <h5><c:out value="${event.name}" /></h5>
                        </a>
                    </td>
                    <td class="Title Offre">
                        <h5><c:out value="${event.description}" /></h5>
                    </td>
                    <td class="active">
                        <p>
                            <c:set var="totalQuantity" value="0" />
                                <c:forEach items="${event.tickets}" var="ticket">
                                    <c:set var="totalQuantity" value="${totalQuantity + ticket.quantityAvailable}" />
                                </c:forEach>
                            ${totalQuantity}
                        </p>
                    </td>
                    <td class="date">
                        <p><c:out value="${event.date}" /></p>
                    </td>
                    <td class="UD">
                        <c:set var="dataTickets" value="["/>
                        <c:forEach items="${event.tickets}" var="ticket" varStatus="loopTickets">
                            <c:set var="delimeter"  value="${not loopTickets.last ? ',' : ']'}" />
                            <c:set var='ticketJson' value='{"ticketValue": "${ticket.type.name()}","ticketPrice":"${ticket.price}" ,"ticketQuantity": "${ticket.quantityAvailable}","ticketType": "${ticket.type.ordinal()}"}${delimeter}' />
                            <c:set var="dataTickets" value="${dataTickets}${ticketJson}" />
                        </c:forEach>
                        <a href="#" class="edit-event-btn" data-modal-target="modalEditEvent" data-modal-toggle="modalEditEvent"
                            data-name="<c:out value="${event.name}" />"
                           data-category="<c:out value="${event.category.id}" />"
                           data-date="<c:out value="${event.date}" />"
                           data-description="<c:out value="${event.description}" />"
                            data-ticket="<c:out value="${dataTickets}" />"
                           data-idEvent="<c:out value="${event.id}" />"
                           >Edit</a>
                        <a class="cursor-pointer" data-modal-target="deleteEvent" data-modal-toggle="deleteEvent">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pagination">
        <a href="#">&laquo;</a>
        <a href="#">1</a>
        <a class="active" href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">6</a>
        <a href="#">&raquo;</a>
    </div>