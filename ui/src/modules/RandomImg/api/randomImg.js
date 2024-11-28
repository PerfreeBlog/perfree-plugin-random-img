export function randomImgPageApi(data) {
    return axios.post('/api/auth/randomImg/page', data);
}

export function randomImgAddApi(data){
    return axios.post('/api/auth/randomImg/add', data);
}

export function randomImgUpdateApi(data){
    return axios.post('/api/auth/randomImg/update', data);
}

export function randomImgDelApi(id) {
    return axios.delete('/api/auth/randomImg/del?id=' + id);
}

export function randomImgGetApi(id) {
    return axios.get('/api/auth/randomImg/get?id=' + id);
}

export function randomImgListAllApi() {
    return axios.get('/api/auth/randomImg/listAll');
}

export function randomImgExportExcelApi(data) {
    return axios.post('/api/auth/randomImg/export', data, {responseType: 'blob'});
}

export function initAttachLibraryListApi(){
    return axios.get('/api/auth/randomImg/listAllImgAttachLibrary');
}