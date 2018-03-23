<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="/css/lq/composition.css">
    <link rel="stylesheet" href="/assert/datatables.css">

    <title>Detail</title>
</head>
<body>
<header>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="#">LengQIU</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
    </nav>
</header>
<!-- Begin page content -->
<main role="main" class="container">
    <div class="mt-5">&nbsp;</div>
    <h2>接口名称: ${thisapi.title}</h2>
    <h5>apiDetail</h5>
    <table id="example" class="table table-detail table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>ParamId</th>
            <th>Name</th>
            <th>parentId</th>
            <th>Type</th>
            <th>Scope</th>
            <th>ScopeDetail</th>
            <th>active</th>
        </tr>
        </thead>
        <tbody>
        <#list apiDetail as detail>
        <tr>
            <td>${detail.reqId}-${detail.level}</td>
            <td><span class="level${detail.level}"></span>${detail.name}</td>
            <td>${detail.parentId}</td>
            <td>${detail.type}</td>
            <td>${detail.scope}</td>
            <td>${detail.scopeDetail}</td>
            <td>${detail.active}</td>
        </tr>
        </#list>
        </tbody>

    </table>
</main>

<tbody id="apilist-tbody-content">
</tbody>


<footer class="footer" id="footer">
    <div class="container">
        <span class="text-muted">这是底部栏......</span>
    </div>
</footer>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!--<script src="js/jquery-3.3.1.min.js"></script>-->
<script src="/js/jquery-1.12.4.js"></script>
<script src="/js/bootstrap/popper.min.js"></script>
<script src="/js/bootstrap/bootstrap.min.js"></script>
<script src="/assert/datatables.js"></script>
<script src="/assert/jquery.spring-friendly.js"></script>
</body>
</html>