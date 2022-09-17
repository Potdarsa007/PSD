import React from "react";

function NotAuthorized() {

return(
    <div class="page-wrap d-flex flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 text-center">
                <div class="mb-4 lead">Sorry you are not authorized for the requested page.</div>
                <a href="/" class="btn btn-link">Back to Home</a>
            </div>
        </div>
    </div>
</div>)
}
export default NotAuthorized;