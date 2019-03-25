<%-- 
    Document   : juros
    Created on : Mar 22, 2019, 3:36:46 PM
    Author     : Raimundo Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>AJAX based CRUD operations using jTable in Servlet and JSP</title>
        <!-- Include one of jTable styles. -->
        <link href="../../jtable/css/metro/crimson/jtable.css" rel="stylesheet" type="text/css" />
        <link href="../../jtable/css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
        <!-- Include jTable script file. -->
        <script src="../../jtable/js/jquery-1.8.2.js" type="text/javascript"></script>
        <script src="../../jtable/js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
        <script src="../../jtable/js/jquery.jtable.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                
                $('#PersonTableContainer').jtable({
                    title: 'Table of people',
                    paging: false,
                    sorting: false, //this is an ajax sort
                    clientSort: true, //this needs jquery.tablesorter.min.js
                    columnResizable: false,
                    columnSelectable: false,
                    selecting: false,
                    multiselect: false,
                    selectingCheckboxes: false,
                    actions: {
                        listAction: '/microsapp/CRUDController?action=list',
                        createAction: '/microsapp/CRUDController?action=create',
                        updateAction: '/microsapp/CRUDController?action=update',
                        deleteAction: '/microsapp/CRUDController?action=delete'
                    },
                    fields: {
                        userid: {
                            title: 'S.NO',
                            key: true,
                            list: true,
                            create: true
                        },
                        firstName: {
                            title: 'First Name',
                            width: '30%',
                            edit: false
                        },
                        lastName: {
                            title: 'Last Name',
                            width: '30%',
                            edit: true
                        },
                        email: {
                            title: 'Email',
                            width: '20%',
                            edit: true
                        }
                    }
                });
                $('#PersonTableContainer').jtable('load');
            });
        </script>
    </head>
    <body>
        <div style="width:60%;margin-right:20%;margin-left:20%;text-align:center;">

            <div id="PersonTableContainer"></div>
        </div>
    </body>
</html>