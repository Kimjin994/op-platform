import Http from '../http';

export const getTableList = function() {
    return Http.post('/table/getTableList')
}

export const doCodeGen = function(data: any) {
    return Http.post('/generate/doGenerate', data)
}
