function list() {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/api/player/list",
        success: function (data) {
            let html = ``;
            $.each(data.content, function (index, el) {
                html += `
                <div class="col-12 col-md-6 col-lg-3 player-item">
                    <div class="card shadow-sm border-0 mb-4">
                        <div class="card-body text-center">
                            <img src="https://banner2.cleanpng.com/20180324/zcq/kisspng-football-player-cartoon-football-5ab6a6e980f891.5574950615219197215283.jpg"
                            class="card-img-top">
                              <h5 class="mt-3 mb-2">${el.name}</h5>
                              <p class="text-muted mb-1">${el.dob} </p>
                              <div class="d-flex justify-content-center">
                          
                            <a data-id="${el.id}" class="btn btn-outline-info mx-1 btn-sm edit">
                            <i class="fas fa-edit"></i>
                            </a>
                            <a data-id="${el.id}" class="btn btn-outline-info mx-1 btn-sm detail">
                                <i class="fa-solid fa-eye"></i>
                            </a>
                            <a class="btn btn-outline-info mx-1 btn-sm salary">
                            <i class="fa-solid fa-money-bill"></i>
                            </a>
                            <a data-id="${el.id}" class="btn btn-outline-danger mx-1 btn-sm delete">
                            <i class="fas fa-trash-alt"></i>
                            </a>
    
                          </div>
                        </div>
                    </div>
                    
                </div>
                
                `
            })

            $("#body").html(html);

            $(".delete").click(function () {
                let id = $(this).data().id
                if (confirm("xóa hả,  chắc chưa")) {
                    deletePlayer(id);
                    showAlert("Xóa thành công", "message")
                }

            })
            $(".detail").click(function () {
                let id = $(this).data().id
                detail(id);
            })
        }
    })
}

function detail(id) {

    $.ajax({
        type: "get",
        url: "http://localhost:8080/api/player/" + id,
        success: function (data) {

            let html = `
       <div class="card mb-3" >
  <div class="row g-0">
    <div class="col-md-4">
      <img src="https://banner2.cleanpng.com/20180324/zcq/kisspng-football-player-cartoon-football-5ab6a6e980f891.5574950615219197215283.jpg"
                             class="card-img-top">
    </div>
    <div class="col-md-8">
      <div class="card-body">
        <h5 class="card-title">${data.name}</h5>
        <p class="card-text">${data.dob}</p>
        <p class="card-text">${data.experience}</p>
        <p class="card-text">${data.position}</p>
        <p class="card-text">${data.club.name}</p>
        <p class="card-text"><small class="text-body-secondary">vip ro 123</small></p>
        <button class="btn btn-primary backHome">Quay lại</button>
      </div>
    </div>
  </div>
</div>
       `
            $("#body").html(html)
            $(".backHome").click(function (){
                $("#body").remove();
                list()
            })

        }
    })
}

function deletePlayer(id) {
    $.ajax({
        type: "delete",
        url: "http://localhost:8080/api/player/" + id,
        success: function () {
            list()
        }
    })

}

list()

$("#showFormAdd").click(function (e) {
    e.preventDefault();
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "get",
        url: "http://localhost:8080/api/club/clubs",
        success: function (data) {

            let options = '';
            $.each(data, function (index, el) {
                options += `<option id="club"  value="${el.id}" >${el.name}</option>`;
            })
            let html = (`
<div class="container w-50 mt-5 mx-auto rounded-4">
    <div class="p-5 shadow">
            <div class="mb-3">
                <label for="name" class="form-label">Họ tên</label>
                <input type="text" class="form-control" id="name1" >
                
            </div>
            <div class="mb-3">
                <label for="dob" class="form-label">Ngày sinh</label>
                <input type="date" class="form-control" id="dob" >

            </div>
            <div class="mb-3">
                <label for="experience" class="form-label">Kinh nghiệm (năm)</label>
                <input type="number" class="form-control" id="experience"  placeholder="bao nhiêu năm kinh nghiệm ??">

            </div>

            <div class="mb-3">
                <label for="position" class="form-label">Vị trí</label>
                <select name="position" class="form-select" id="position" aria-label="Default select example" >
                    <option name="position" value="">Vị trí</option>
                    <option name="position"  value="Tiền đạo" >Tiền đạo</option>
                    <option name="position"  value="Tiền vệ" >Tiền vệ</option>
                    <option name="position"  value="Trung vệ" >Trung vệ</option>
                    <option name="position"  value="Hậu vệ" >Hậu vệ</option>
                    <option name="position"  value="Thủ môn" >Thủ môn</option>
                </select>

            </div>
            <div class="mb-3">
                <label for="club" class="form-label">Đội bóng</label>
                <select class="form-select" id="club" aria-label="Default select example" >
                    ${options}
                </select>
            </div>
            <button type="submit" class="btn btn-primary save">Lưu</button>
            <a  class="btn btn-primary back" >Thoát</a>
    </div>
</div>
`)
            $("#body").html(html);
            $(".save").click(function () {
                savePlayer();
                showAlert("Thêm thành công", "message")
            })
            $(".back").click(function (e) {

                list();
            })
        }
    });
})

const savePlayer = () => {
    let name1 = $("#name1").val();
    let dob1 = $("#dob").val();
    let experience1 = $("#experience").val();
    let position1 = $("#position").val();
    let club1 = $("#club").val();

    let newPlayer = {
        name: name1,
        dob: dob1,
        experience: experience1,
        position: position1,
        status: "Dự bị",
        club: {
            id: club1
        },
    }
    console.log(newPlayer)

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "post",
        data: JSON.stringify(newPlayer),
        url: "http://localhost:8080/api/player/create",
        success: function () {
            list();
        },
        error: function () {
            showAlert("Thêm thất bại", "error");
        }
    })
}
const showAlert = (message, type, text) => {
    Swal.fire({
        icon: type,
        title: message,
        text: text
    });
}


$(".enterSize").click(function () {
    let size = $("#size").val();
    let page = 0;
    let name = $("#name").val();
    let dobMin = "";
    let dobMax = "";


    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "get",
        url: `http://localhost:8080/api/player/list?size=${size}&&name=${name}`,
        success: function (data) {
            console.log(data)
            let html = ``;
            $.each(data.content, function (index, e) {
                html += `
                <div class="col-12 col-md-6 col-lg-3 player-item">
                            <div class="card shadow-sm border-0 mb-4">
                                <div class="card-body text-center">
                                    <img src="https://banner2.cleanpng.com/20180324/zcq/kisspng-football-player-cartoon-football-5ab6a6e980f891.5574950615219197215283.jpg"
                             class="card-img-top">
                                  <h5 class="mt-3 mb-2">${e.name}</h5>
                                  <p class="text-muted mb-1">${e.dob} </p>
                                  <div class="d-flex justify-content-center">
                                  
                                    <a class="btn btn-outline-info mx-1 btn-sm edit">
                                    <i class="fas fa-edit"></i>
                                    </a>
                                    <a class="btn btn-outline-info mx-1 btn-sm detail">
                                        <i class="fa-solid fa-eye"></i>
                                    </a>
                                    <a class="btn btn-outline-info mx-1 btn-sm salary">
                                    <i class="fa-solid fa-money-bill"></i>
                                    </a>
                                    <a class="btn btn-outline-danger mx-1 btn-sm delete">
                                    <i class="fas fa-trash-alt"></i>
                                    </a>
        
                                  </div>
                                </div>
                            </div>
                            </div>
                `
            })
            $("#body").html(html);
        },

    })
})


