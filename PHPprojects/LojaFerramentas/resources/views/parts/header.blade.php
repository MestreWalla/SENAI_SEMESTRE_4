<!-- Header -->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container">
        <a class="navbar-brand" href="#">Minha Loja</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="/">Início</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/produtos">Produtos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Sobre</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contato</a>
                </li>
                @if (Auth::check())
                    @if (Auth::user()->user_type === 'administrador')
                        <li class="nav-item">
                            <a href="/dashboard" class="nav-link">
                                <p>Dashboard</p>
                            </a>
                        </li>
                    @endif
                @endif
            </ul>
            @if (Auth::check())
                <form class="d-flex search-bar" action="/logout" method="post">
                    @csrf
                    {{-- <button class="btn btn-danger" type="submit">Logout em {{Auth::user()->name}}</button> --}}
                    <button class="btn btn-warning" type="submit" onmouseover="this.textContent = 'Logout';"
                        onmouseout="this.textContent = '{{ Auth::user()->name }}';">
                        {{ Auth::user()->name }}
                    </button>
                </form>
            @else
                @csrf
                <a href="/login" class="btn btn-success">Login</a>
            @endif
            {{-- <form class="d-flex search-bar">
                <input class="form-control me-2" type="search" placeholder="Buscar..." aria-label="Search">
                <button class="btn" type="submit">Buscar</button>
            </form> --}}
            <div class="icons">
                <a href="#"><i class="fas fa-shopping-cart"></i></a>
                <a href="#"><i class="fas fa-user"></i></a>
            </div>
        </div>
    </div>
</nav>
