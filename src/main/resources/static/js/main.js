var baseURL = new URL(window.location.origin)

var renderCitation = function (citation) {
  return '<li>'+ citation.contenu +' &mdash;'+ citation.auteur +'</li>'
}

var renderListeCitations = function (citations) {
  return '<ul>'+ citations.map(renderCitation).join('') +'</ul>'
}

var installerListeCitations = function (listeCitationsHtml) {
  document.getElementById('liste-citations').innerHTML = listeCitationsHtml
}

var fetchCitations = function (url) {
  fetch(url).then(function(resp) {
    return resp.json()
  }).then(function (data) {
    installerListeCitations(renderListeCitations(data))
  })
}

var rechercher = function (terms) {
  var url = new URL('/citations/contenu', baseURL)
  terms.forEach(function (t) { url.searchParams.append('term', t) })
  fetchCitations(url)
}

var lierFormulaire = function () {
  var form = document.getElementById('search-form')
  var input = document.getElementById('search-input')
  form.addEventListener('submit', function (e) {
    e.preventDefault()
    rechercher(input.value.split(/\s+/))
  })
}

document.addEventListener('DOMContentLoaded', function () {
  fetchCitations(new URL('/citations', baseURL))
  lierFormulaire()
})
