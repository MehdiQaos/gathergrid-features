<nav>
    <div class="n1">
        <div>
            <i id="menu-bar" class="fas fa-bars" onclick="toggleSideBar()"></i>
        </div>
        <div class="search">
            <i class="far fa-search"></i>
            <input type="text" placeholder="seach for a job" />
        </div>
    </div>
    <div class="profile">
        <i class="far fa-bell notification"></i>
        <div class="flex flex-col justify-center justify-center">
            <img src="avatarProfile.png" />
            <button id="dropdownVousButton" data-dropdown-toggle="dropdownVous" class="flex items-center">
                <p class="text-sm">vous</p>
                <i class="fa-duotone fa-caret-down text-sm px-0.5" style="--fa-secondary-opacity: 1"></i>
            </button>
        </div>
    </div>
</nav>
<script>
    function toggleSideBar() {
        $("#sideBar").toggleClass('active')
    }
</script>
<!---drop down--->
<div id="dropdownVous"
     class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600">
    <div class="px-4 py-3 text-sm text-gray-900 dark:text-white">
        <div>Bonnie Green</div>
        <div class="font-medium truncate">name@flowbite.com</div>
    </div>
    <ul class="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownVousButton">
        <li>
            <a href="${pageContext.request.contextPath}/Dashboard"
                         class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">Dashboard
            </a>
        </li>
        <li>
            <a  href="${pageContext.request.contextPath}/profile"
                         class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">profile
            </a>
        </li>
    </ul>
    <div class="py-2">
        <a href="#"
           class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">Sign
            out</a>
    </div>
</div>