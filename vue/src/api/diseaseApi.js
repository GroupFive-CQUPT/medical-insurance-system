import * as http from "node:http";

function findAllDisease(data) {
    return http.get('/disease/findAllDisease', { params: data })

}
export default {
    findAllDisease
}