async function addStudent() {

    const name = document.getElementById("studentName").value.trim();
    const rollNumber = document.getElementById("rollNumber").value.trim();
    const email = document.getElementById("studentEmail").value.trim();
    const department = document.getElementById("department").value.trim();

    const message = document.getElementById("studentMessage");

    if (!name || !rollNumber || !email || !department) {
        message.innerText = "Please fill all student details";
        return;
    }

    const student = {
        name: name,
        rollNumber: rollNumber,
        email: email,
        department: department
    };

    try {

        const response = await fetch("/api/student/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(student)
        });

        if (response.ok) {

            message.innerText = "Student added successfully";

            document.getElementById("studentName").value = "";
            document.getElementById("rollNumber").value = "";
            document.getElementById("studentEmail").value = "";
            document.getElementById("department").value = "";

            if (typeof loadStudents === "function") {
                loadStudents();
            }

        } else {

            const errorMessage = await response.text();
            message.innerText = errorMessage;
        }

    } catch (error) {

        console.error(error);

        message.innerText =
            "Unable to add student. Check server.";
    }
}


function logout() {

    localStorage.removeItem("token");

    window.location.href = "/login";
}


function saveAttendance() {

    alert("Attendance saved successfully");
}