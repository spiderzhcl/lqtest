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

    <title>HomePage</title>
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
    <h2>模块列表</h2>
    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>id</th>
            <th>Type</th>
            <th>name</th>
            <th>domain</th>
            <th>ipaddr</th>
            <th>location</th>
            <th>upstream1</th>
        </tr>
        </thead>
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
<script>
    $(document).ready(function () {
        console.log("prepare call ajax.");
        $('#example').DataTable({
                                    "processing": true,
                                    "serverSide": true,
                                    "ajax": {
                                        "url": "/module/listall"
                                    },
                                    "columns": [
                                        {"data": "id"},
                                        {"data": "type"},
                                        {"data": "name"},
                                        {"data": "domain"},
                                        {
                                            "data": "ipaddr",
                                            "render": function (ipaddr) {
                                                if (ipaddr.indexOf("168.21.") > 0) {
                                                    return '<span class="btn-warning">' + ipaddr
                                                           + '</span>'
                                                } else if (ipaddr.indexOf("168.2.") > 0
                                                           || ipaddr.indexOf("168.1.") > 0) {
                                                    return '<span class="btn-primary">' + ipaddr
                                                           + '</span>'
                                                } else {
                                                    return ipaddr;
                                                }
                                            }
                                        },
                                        {
                                            "data": "location",
                                            "render": function (location) {
                                                if (location === "SM") {
                                                    return '<span class="btn-warning">' + location
                                                           + '</span>'
                                                } else if (location === "SJ") {
                                                    return '<span class="btn-primary">' + location
                                                           + '</span>'
                                                } else {
                                                    return location;
                                                }
                                            }
                                        },
                                        {
                                            "data": 'upstream1',
                                            "render": function (upstream1) {
                                                return upstream1;
                                            }
                                        }
                                    ]
                                });
    });

</script>
</body>
</html>